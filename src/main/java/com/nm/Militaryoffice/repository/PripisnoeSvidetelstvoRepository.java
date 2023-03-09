package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.dao.PripisnoeSvidetelstvoDao;
import com.nm.Militaryoffice.model.PripisnoeSvidetelstvo;
import com.nm.Militaryoffice.repository.mapper.PripisnoeSvidetelstvoMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.sql.Types;
import java.util.Optional;

@Repository
public class PripisnoeSvidetelstvoRepository implements PripisnoeSvidetelstvoDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PripisnoeSvidetelstvoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(
            @NotNull PripisnoeSvidetelstvo pripisnoeSvidetelstvo,
            @NotEmpty String series_and_number,
            long conscriptId
    ) {
        String sql = """
                insert into pripisnoe_svidetelstvo (
                series_and_number,
                health_category,
                who_isseud,
                commissioner_fullname,
                issue_date,
                date_of_appearance
                )
                values (
                :series_and_number,
                :health_category,
                :who_issued,
                :commissioner_fullname,
                :issue_date,
                :date_of_appearance
                );
                
                UPDATE Conscript set series_and_number = :series_and_number
                WHERE id = :id;
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("series_and_number", series_and_number)
                .addValue("health_category", pripisnoeSvidetelstvo.getHealthCategory())
                .addValue("who_issued", pripisnoeSvidetelstvo.getWhoIssued())
                .addValue("commissioner_fullname", pripisnoeSvidetelstvo.getCommissionerFullname())
                .addValue("issue_date", pripisnoeSvidetelstvo.getIssueDate())
                .addValue("date_of_appearance", pripisnoeSvidetelstvo.getDateOfAppearance())
                .addValue("id", conscriptId);

        return (long) jdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public boolean update(PripisnoeSvidetelstvo pripisnoeSvidetelstvo) {
        return false;
    }

    @Override
    public Optional<PripisnoeSvidetelstvo> findBySeriesAndNumber(String seriesAndNumber) {
        final String sql = """
                SELECT
                    series_and_number,
                    health_category,
                    who_isseud,
                    commissioner_fullname,
                    issue_date,
                    date_of_appearance
                FROM "pripisnoe_svidetelstvo"
                WHERE series_and_number = ?
                """;
        return jdbcTemplate.getJdbcTemplate()
                .query(
                        sql,
                        new Object[] {seriesAndNumber},
                        new int[] {Types.VARCHAR},
                        new PripisnoeSvidetelstvoMapper()
                )
                .stream().findFirst();
    }

    public boolean isThisSeriesAndNumberFree(String seriesAndNumber) {
        final String sql = """
                SELECT COUNT(series_and_number)
                FROM pripisnoe_svidetelstvo
                WHERE series_and_number = :seriesAndNumber
                """;
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("seriesAndNumber", seriesAndNumber);
        return jdbcTemplate.queryForObject(sql, parameterSource, Integer.class) == 0;
    }
}

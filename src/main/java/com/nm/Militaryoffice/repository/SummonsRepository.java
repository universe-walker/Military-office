package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Summons;
import com.nm.Militaryoffice.repository.mapper.SummonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SummonsRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    SummonsRepository(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Summons> getSummonsByConscriptId(Long conscriptId) {
        var sql = """
                   SELECT
                    summons_id,
                    conscription_id,
                    id,
                    creation_date,
                    content,
                    who_made_summons,
                    appearance_datetime,
                    destination_address,
                    is_received,
                    is_appeared
                   FROM summons
                   WHERE id = ?;""";
        return namedJdbcTemplate.getJdbcTemplate()
                .query(sql, new SummonsMapper(), conscriptId);
    }

    public int createSummons(Summons summons) {
        var sql = """
                INSERT INTO
                    Summons
                    (id,
                    conscription_id,
                    creation_date,
                    content,
                    who_made_summons,
                    appearance_datetime,
                    destination_address,
                    is_received,
                    is_appeared)
                VALUES
                    (:id,
                    :conscription_id,
                    :creation_date,
                    :content,
                    :who_made_summons,
                    :appearance_datetime,
                    :destination_address,
                    :is_received,
                    :is_appeared);
                """;
        var parameters = new MapSqlParameterSource()
                .addValue("id", summons.getId())
                .addValue("conscription_id", summons.getConscription_id())
                .addValue("creation_date", summons.getCreation_date())
                .addValue("content", summons.getContent())
                .addValue("who_made_summons", summons.getWho_made_summons())
                .addValue("appearance_datetime", summons.getAppearance_datetime())
                .addValue("destination_address", summons.getDestination_address())
                .addValue("is_received", summons.is_received())
                .addValue("is_appeared", summons.is_appeared());

        return namedJdbcTemplate.update(sql, parameters);
    }
}

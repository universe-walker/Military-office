package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Education;
import com.nm.Militaryoffice.repository.mapper.EducationMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    EducationRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Education> getAllEducationsByConscriptId(Long conscriptId) {
        var sql = """
            SELECT
                id_education,
                id,
                education_level,
                location,
                edu_sart_date,
                edu_end_date
            FROM education
            WHERE id = ?
        """;

        return namedJdbcTemplate.getJdbcTemplate()
                .query(
                        sql,
                        new EducationMapper(),
                        conscriptId
                );
    }

    public void createEducation(@NotNull Education education, Long conscriptId) {
        var sql = """
                INSERT INTO Education (id, education_level, location, edu_sart_date, edu_end_date)
                VALUES (:id, :education_level, :location, :edu_start_date, :edu_end_date);
                """;
//        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(education);
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", education.getId())
                .addValue("education_level", education.getEducation_level())
                .addValue("location", education.getLocation())
                .addValue("edu_start_date", education.getEdu_start_date())
                .addValue("edu_end_date", education.getEdu_end_date());

        namedJdbcTemplate.update(sql, namedParameters);
    }
}

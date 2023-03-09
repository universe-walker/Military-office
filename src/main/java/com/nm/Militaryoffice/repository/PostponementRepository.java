package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Postponement;
import com.nm.Militaryoffice.repository.mapper.PostponementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostponementRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    
    PostponementRepository(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Postponement> getPostponementsByConscriptId(Long conscriptId) {
        var sql = "SELECT * FROM Postponement WHERE id = ?";

        return namedJdbcTemplate.getJdbcTemplate()
                .query(
                        sql,
                        new PostponementMapper(),
                        conscriptId
                );
    }

    public void createPostponement(Long conscriptId, Postponement postponement) {
        var sql = """
                INSERT INTO Postponement (
                    id,
                    paragraph_of_document,
                    postponement_start_date,
                    postponement_end_date
                )
                VALUES (
                    :id,
                    :paragraph_of_document,
                    :postponement_start_date,
                    :postponement_end_date
                )
                """;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", conscriptId)
                .addValue("paragraph_of_document", postponement.getParagraph_of_document())
                .addValue("postponement_start_date",postponement.getPostponement_start_date())
                .addValue("postponement_end_date", postponement.getPostponement_end_date());

        namedJdbcTemplate.update(sql, parameterSource);
    }
}

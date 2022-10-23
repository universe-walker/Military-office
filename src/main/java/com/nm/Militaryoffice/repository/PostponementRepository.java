package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Postponement;
import com.nm.Militaryoffice.repository.mapper.PostponementMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}

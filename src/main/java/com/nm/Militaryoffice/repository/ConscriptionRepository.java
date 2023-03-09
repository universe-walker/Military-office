package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.repository.mapper.ConscriptionMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConscriptionRepository {
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ConscriptionRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Conscription> getAllConscriptions() {
        var sql = """
                SELECT 
                    conscription_id,
                    conscription_text,
                    start_date,
                    end_date,
                    quantity_plan
                FROM Conscription
                """;
        return namedParameterJdbcTemplate
                .query(sql, new ConscriptionMapper());
    }

    public Conscription getConscription(Long conscriptionId) {
        var sql = """
                SELECT * FROM Conscription WHERE conscription_id = :id;
                """;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", conscriptionId);
        return namedParameterJdbcTemplate.
                query(sql, parameterSource, new ConscriptionMapper())
                .get(0);
    }


}

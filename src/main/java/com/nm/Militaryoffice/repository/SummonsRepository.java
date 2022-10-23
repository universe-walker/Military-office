package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.Summons;
import com.nm.Militaryoffice.repository.mapper.SummonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}

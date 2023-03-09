package com.nm.Militaryoffice.repository;

import com.nm.Militaryoffice.model.CommissionDecision;
import com.nm.Militaryoffice.repository.mapper.CommissionDecisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommissionDecisionRepository {
    public CommissionDecisionRepository(
            @Autowired NamedParameterJdbcTemplate namedJdbcTemplate
    ) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<CommissionDecision> getCommissionDecisionByConscriptId(final Long conscriptId) {
        var sql = """
                SELECT
                    decision_id,
                    id,
                    decision_text,
                    organisation_that_made_decision,
                    commissioner_fullname,
                    is_enlistment,
                    is_conscript_signed,
                    decision_date
                FROM commission_decision
                WHERE
                    id = ?;
                """;
        return namedJdbcTemplate.getJdbcTemplate()
                .query(
                        sql,
                        new CommissionDecisionMapper(),
                        conscriptId
                );
    }

    public void save(CommissionDecision decision, long conscriptId) {
        var sql = """
                INSERT INTO commission_decision (
                    id,
                    decision_text,
                    organisation_that_made_decision,
                    commissioner_fullname,
                    is_enlistment,
                    is_conscript_signed
                )
                VALUES (
                    :id,
                    :decision_text,
                    :organisation_that_made_decision,
                    :commissioner_fullname,
                    :is_enlistment,
                    :is_conscript_signed
                )
                """;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", conscriptId)
                .addValue("decision_text", decision.getDecision_text())
                .addValue("organisation_that_made_decision", decision.getOrganisation_that_made_decision())
                .addValue("commissioner_fullname", decision.getCommissioner_fullname())
                .addValue("is_enlistment", decision.getIs_enlistment())
                .addValue("is_conscript_signed", decision.getIs_conscript_signed());

        namedJdbcTemplate.update(sql, parameterSource);
    }
}

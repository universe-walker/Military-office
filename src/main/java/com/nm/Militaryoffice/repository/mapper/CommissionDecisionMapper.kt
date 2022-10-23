package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.CommissionDecision
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class CommissionDecisionMapper: RowMapper<CommissionDecision> {
    override fun mapRow(rs: ResultSet, rowNum: Int): CommissionDecision {
        val decision = CommissionDecision()
        decision.id = rs.getLong("id")
        decision.decision_id = rs.getInt("decision_id")
        decision.decision_text = rs.getString("decision_text")
        decision.organisation_that_made_decision = rs.getString("orgasnisation_that_made_decision")
        decision.commissioner_fullname = rs.getString("commissioner_fullname")
        decision.is_enlistment = rs.getBoolean("is_enlistment")
        decision.is_conscript_signed = rs.getBoolean("is_conscript_signed")
        return decision
    }
}
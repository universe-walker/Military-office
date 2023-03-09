package com.nm.Militaryoffice.repository.mapper;

import com.nm.Militaryoffice.model.CommissionDecision;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommissionDecisionMapper implements RowMapper<CommissionDecision> {

    @Override
    public CommissionDecision mapRow(ResultSet rs, int rowNum) throws SQLException {
        var decision = new CommissionDecision();
        decision.setDecision_id(rs.getLong("decision_id"));
        decision.setId(rs.getLong("id"));
        decision.setDecision_date(rs.getDate("decision_date"));
        decision.setDecision_text(rs.getString("decision_text"));
        decision.
                setOrganisation_that_made_decision(rs.
                        getString("organisation_that_made_decision"));
        decision.setCommissioner_fullname(rs.getString("commissioner_fullname"));
        decision.setIs_enlistment(rs.getBoolean("is_enlistment"));
        decision.setIs_conscript_signed(rs.getBoolean("is_conscript_signed"));

        return decision;
    }
}

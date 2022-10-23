package com.nm.Militaryoffice.repository.mapper;

import com.nm.Militaryoffice.model.PripisnoeSvidetelstvo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PripisnoeSvidetelstvoMapper implements RowMapper<PripisnoeSvidetelstvo> {

    @Override
    public PripisnoeSvidetelstvo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PripisnoeSvidetelstvo pSvidetelstvo = new PripisnoeSvidetelstvo();
        pSvidetelstvo.setSeriesAndNumber(rs.getString("series_and_number"));
        pSvidetelstvo.setHealthCategory(rs.getString("health_category"));
        pSvidetelstvo.setWhoIssued(rs.getString("who_isseud"));
        pSvidetelstvo.setCommissionerFullname(rs.getString("commissioner_fullname"));
        pSvidetelstvo.setIssueDate(rs.getDate("issue_date"));
        pSvidetelstvo.setDateOfAppearance(rs.getDate("date_of_appearance"));
        return pSvidetelstvo;
    }
}

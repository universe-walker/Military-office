package com.nm.Militaryoffice.repository.mapper;

import com.nm.Militaryoffice.model.Conscript;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConscriptMapper implements RowMapper<Conscript> {
    @Override
    public Conscript mapRow(ResultSet rs, int rowNum) throws SQLException {
        Conscript conscript = new Conscript();
        conscript.setId(rs.getLong("id"));
        conscript.setSeriesAndNumber(rs.getString("series_and_number"));
        conscript.setPassport(rs.getString("passport"));
        conscript.setName(rs.getString("name"));
        conscript.setPatronymic(rs.getString("patronymic"));
        conscript.setSurname(rs.getString("surname"));
        conscript.setGender(rs.getString("gender"));
        conscript.setDateOfBirth(rs.getDate("date_of_birth"));
        conscript.setPlaceOfBirth(rs.getString("place_of_birth"));
        conscript.setPlaceOfResidence(rs.getString("place_of_residence"));
        conscript.setOccupation(rs.getString("occupation"));
        conscript.setMaritalStatus(rs.getString("marital_status"));
        conscript.setSportsCategory(rs.getString("sports_category"));
        return conscript;
    }
}

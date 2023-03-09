package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.Education
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class EducationMapper : RowMapper<Education> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Education {
        val edu = Education()
        edu.setId_education(rs.getLong("id_education"))
        edu.setId(rs.getLong("id"))
        edu.setEducation_level(rs.getString("education_level"))
        edu.setLocation(rs.getString("location"))
        edu.setEdu_start_date(rs.getDate("edu_sart_date"))
        edu.setEdu_end_date(rs.getDate("edu_end_date"))

        return edu
    }
}
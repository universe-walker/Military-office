package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.Education
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class EducationMapper : RowMapper<Education> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Education {
        val edu = Education()
        edu.id_education = rs.getLong("id_education")
        edu.id = rs.getLong("id")
        edu.education_level = rs.getString("education_level")
        edu.location = rs.getString("location")
        edu.edu_start_date = rs.getDate("edu_sart_date")
        edu.edu_end_date = rs.getDate("edu_end_date")

        return edu
    }
}
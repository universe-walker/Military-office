package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.Postponement
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class PostponementMapper : RowMapper<Postponement> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Postponement {
        val postponement = Postponement()
        postponement.postponement_id = rs.getInt("postponement_id")
        postponement.id = rs.getLong("id")
        postponement.paragraph_of_document = rs.getString("paragraph_of_document")
        postponement.postponement_start_date = rs.getDate("postponement_start_date")
        postponement.postponement_end_date = rs.getDate("postponement_end_date")

        return postponement
    }

}
package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.Summons
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class SummonsMapper : RowMapper<Summons> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Summons {
        val summons = Summons()
        summons.summons_id = rs.getInt("summons_id")
        summons.conscription_id = rs.getInt("conscription_id")
        summons.id = rs.getLong("id")
        summons.creation_date = rs.getDate("creation_date")
        summons.content = rs.getString("content")
        summons.who_made_summons = rs.getString("who_made_summons")
        summons.appearance_datetime = rs.getDate("appearance_datetime")
        summons.destination_address = rs.getString("destination_address")
        summons.is_received = rs.getBoolean("is_received")
        summons.is_appeared = rs.getBoolean("is_appeared")

        return summons
    }

}
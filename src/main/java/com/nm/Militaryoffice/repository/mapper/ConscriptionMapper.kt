package com.nm.Militaryoffice.repository.mapper

import com.nm.Militaryoffice.model.Conscription
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ConscriptionMapper: RowMapper<Conscription> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Conscription {
        val conscription = Conscription()
        conscription.conscription_id = rs.getInt("conscription_id")
        conscription.conscription_text = rs.getString("conscription_text")
        conscription.start_date = rs.getDate("start_date")
        conscription.end_date = rs.getDate("end_date")
        conscription.quantity_plan = rs.getInt("quantity_plan")

        return conscription
    }

}
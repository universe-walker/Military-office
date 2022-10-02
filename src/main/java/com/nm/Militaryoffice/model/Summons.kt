package com.nm.Militaryoffice.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Summons(@NotNull var summons_id: Int,
              @NotNull var conscription_id: Int,
              @NotNull var id: Long,
              @NotNull var creation_date: Date,
              @NotEmpty @Size(max = 600) var content: String,
              @NotEmpty @Size(max = 300) var who_made_summons: String,
              @NotNull var appearance_datetime: Date,
              @NotEmpty @Size(max = 100) var destination_address: String,
              var is_received: Boolean?,
              var is_appeared: Boolean?
              ) {
    constructor() : this(
        0,
        0,
        0,
        Date(),
        "",
        "",
        Date(),
        "",
        false,
        false
    )
}
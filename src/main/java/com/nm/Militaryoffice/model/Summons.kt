package com.nm.Militaryoffice.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class Summons(@NotNull summons_id: Int,
              @NotNull conscription_id: Int,
              @NotNull id: Long,
              @NotNull creation_date: Date,
              @NotEmpty @Size(max = 600) content: String,
              @NotEmpty @Size(max = 300) who_made_summons: String,
              @NotNull appearance_datetime: Date,
              @NotEmpty @Size(max = 100) destination_address: String,
              is_received: Boolean?,
              is_appeared: Boolean?
              )
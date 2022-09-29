package com.nm.Militaryoffice.model

import java.util.*
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class Postponement(
        @NotNull @Positive postponement_id: Int,
        @NotNull @Positive id: Long,
        @NotEmpty paragraph_of_document: String,
        @NotNull @FutureOrPresent postponement_start_date: Date,
        @NotNull @FutureOrPresent postponement_end_date: Date
)
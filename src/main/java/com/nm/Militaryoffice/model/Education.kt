package com.nm.Militaryoffice.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Education(@NotEmpty var id_education: Long,
                @NotEmpty var id: Long,
                @NotEmpty @Size(max=30) var education_level: String,
                @NotEmpty var location: String,
                @NotNull var edu_start_date: Date,
                var edu_end_date: Date?
                ) {
    constructor() : this(
        0,
        0,
        "",
        "",
        Date(),
        Date()
    )
}
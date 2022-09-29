package com.nm.Militaryoffice.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class Conscription(@NotNull var conscription_id: Int,
                   @NotEmpty var conscription_text: String,
                   @NotNull var start_date: Date,
                   var end_date: Date?,
                   @NotNull var quantity_plan: Int
                   )
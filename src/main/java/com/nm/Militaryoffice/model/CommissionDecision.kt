package com.nm.Militaryoffice.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CommissionDecision(
        @NotNull var  decision_id: Int,
        @NotNull var id: Long,
        @NotEmpty @Size(max = 500) var decision_text: String,
        @NotEmpty @Size(max = 300) var organisation_that_made_decision: String,
        @NotEmpty @Size(max = 100) var commissioner_fullname: String,
        @NotNull var is_enlistment: Boolean,
        var is_conscript_signed: Boolean?
) {
        constructor() : this(
                0,
                0,
                "",
                "",
                "",
                false,
                null
        )
}
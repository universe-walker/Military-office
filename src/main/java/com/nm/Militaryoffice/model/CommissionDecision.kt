package com.nm.Militaryoffice.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CommissionDecision(
        @NotNull decision_id: Int,
        @NotNull id: Long,
        @NotEmpty @Size(max = 500) decision_text: String,
        @NotEmpty @Size(max = 300) organisation_that_made_decision: String,
        @NotEmpty @Size(max = 100) commissioner_fullname: String,
        @NotNull is_enlistment: Boolean,
        is_conscript_signed: Boolean?
)
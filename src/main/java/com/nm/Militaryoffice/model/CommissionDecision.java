package com.nm.Militaryoffice.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDecision {
    private Long decision_id;
    @NotNull
    private Long id;
    @NotEmpty
    @Size(max = 500)
    private String decision_text;
    @NotEmpty
    @Size(max = 300)
    private String organisation_that_made_decision;
    @NotEmpty
    @Size(max = 100)
    private String commissioner_fullname;
    @NotNull
    private Boolean is_enlistment;
    private Boolean is_conscript_signed;
    private Date decision_date;
}

package com.nm.Militaryoffice.controller;

import com.nm.Militaryoffice.model.CommissionDecision;
import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.model.Postponement;
import com.nm.Militaryoffice.service.CommissionDecisionService;
import com.nm.Militaryoffice.service.ConscriptService;
import com.nm.Militaryoffice.service.PostponementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/action/conscript")
public class ActionController {
    private final PostponementService postponementService;
    private final ConscriptService conscriptService;
    private final CommissionDecisionService decisionService;

    public ActionController(
            @Autowired PostponementService postponementService,
            @Autowired ConscriptService conscriptService,
            @Autowired CommissionDecisionService decisionService
    ) {
        this.postponementService = postponementService;
        this.conscriptService = conscriptService;
        this.decisionService = decisionService;
    }

    @GetMapping("/{conscriptId}/create/postponement")
    String showCreatePostponement(
            @PathVariable Long conscriptId,
            Model model
    ) {
        model.addAttribute("postponement", new Postponement());
        model.addAttribute("conscriptId", conscriptId);
        model.addAttribute("postponement", new Postponement());

        return "createPostponement";
    }

    @PostMapping(value = "/{conscriptId}/create/postponement",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String createPostponement(
            @Valid Postponement postponement,
            BindingResult result,
            @PathVariable Long conscriptId,
            Model model
    ) {
        if (result.hasErrors()) return "createPostponement";

        postponementService.createPostponement(conscriptId, postponement);
        return "redirect:/conscript/" + conscriptId;
    }

    @GetMapping("/{conscriptId}/create/commission-decision")
    String showCreateCommissionDecision(@PathVariable long conscriptId, Model model) {
        model.addAttribute("conscriptFullname",
                conscriptService
                        .getConscriptById(conscriptId)
                        .get()
                        .getFullname());
        model.addAttribute("commissionDecision", new CommissionDecision());
        model.addAttribute("conscriptId", conscriptId);
        return "createCommissionDecision";
    }

    @PostMapping(value = "/{conscriptId}/create/commission-decision",
    consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String createCommissionDecision(
            @Valid CommissionDecision decision,
            BindingResult result,
            @PathVariable long conscriptId) {
        if (result.hasErrors()) return "createCommissionDecision";

        decisionService.createCommissionDecision(decision, conscriptId);
        return "redirect:/conscript/" + conscriptId;
    }
}

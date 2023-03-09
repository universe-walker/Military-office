package com.nm.Militaryoffice.controller;

import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.service.SummonsService;
import com.nm.Militaryoffice.model.SummonsInfo;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@AllArgsConstructor
@Controller
public class SummonsController {
    private final SummonsService summonsService;

    @GetMapping("/summons")
    public String summonsMain() {
        return "summons";
    }

    @GetMapping("/summons/generate")
    public String showGenerateSummons(Model model) {
        model.addAttribute("summonsInfo", new SummonsInfo());
        return "generateSummonsPage";
    }

    @PostMapping("/summons/generate")
    public String generateSummons(@Valid SummonsInfo summonsInfo,
                                  BindingResult result) {
        if (result.hasErrors()) return "generateSummonsPage";
        var conscription = new Conscription();
        conscription.setConscription_id(0);
        summonsService.generateSummons(summonsInfo, conscription);

        return "redirect:/summons";
    }
}

package com.nm.Militaryoffice.controller;

import com.nm.Militaryoffice.model.Conscription;
import com.nm.Militaryoffice.service.ConscriptionService;
import com.nm.Militaryoffice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ReportController {
    private final ReportService reportService;
    private final ConscriptionService conscriptionService;

    public ReportController(@Autowired ReportService reportService,
                            @Autowired ConscriptionService conscriptionService) {
        this.reportService = reportService;
        this.conscriptionService = conscriptionService;
    }

    @GetMapping("/create-report")
    public String showCreateReport(Model model) {
        model.addAttribute("conscriptions", conscriptionService.getAllConscription());
        model.addAttribute("conscription_obj", new Conscription());
        return "createReport";
    }

    @PostMapping("/create-report")
    public String createReport(Long conscriptionId) {
        Conscription conscription = conscriptionService.getConscription(conscriptionId);
        try {
            reportService.createReport(conscription);
        } catch (IOException ex) {
            throw new RuntimeException("file exception");
        }

        return "redirect:/main";
    }
}

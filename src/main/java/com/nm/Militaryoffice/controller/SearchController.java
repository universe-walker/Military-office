package com.nm.Militaryoffice.controller;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(@Autowired SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String getSearchMainPage() {
        return "searchMainPage";
    }

    @GetMapping("/result")
    public String searchMainPage(@RequestParam String q, @RequestParam String by,
                                 Model model) {
        List<Conscript> conscript = searchService.search(by, q);
        if (conscript != null || !conscript.isEmpty()) {
            model.addAttribute("results", conscript);
        } else {
            model.addAttribute("results", List.of());
        }
        model.addAttribute("q", q);

        model.addAttribute("findBy", searchService.getFindByRussian(by));
        model.addAttribute("findByEn", by);
        return "searchMainPage";
    }
}

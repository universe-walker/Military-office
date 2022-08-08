package com.nm.Militaryoffice.controller.rest;

import com.nm.Militaryoffice.model.Conscript;
import com.nm.Militaryoffice.service.ConscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/conscript")
public class ConscriptRestController {
    private final ConscriptService conscriptService;

    @Autowired
    public ConscriptRestController(ConscriptService conscriptService) {
        this.conscriptService = conscriptService;
    }
    @PostMapping
    public void addConscript(@RequestBody @Valid Conscript conscript) {
        System.out.println(conscript);
        conscriptService.addConscript(conscript);
    }

    @GetMapping
    public List<Conscript> listConscript() {
        return conscriptService.listConscript();
    }

    @GetMapping(path = "/{id}")
    public Optional<Conscript> findConscriptById(@PathVariable Long id) {
        return conscriptService.getConscriptById(id);
    }

}

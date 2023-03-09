package com.nm.Militaryoffice.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/поиск/{findBy}/{searchValue}")
public class SearchRestController {

    @GetMapping
    public String search(
            @PathVariable("findBy") String findBy,
            @PathVariable("searchValue") String searchValue) {


        //todo

        return "";
    }

}

package com.nm.Militaryoffice.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;

public class Postponement {
    @NotNull
    @Positive
    private Long id;
    private Integer postponement_id;
    @NotEmpty
    private String paragraph_of_document;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy.MM.dd"})
    @NotNull
    private Date postponement_start_date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy.MM.dd"})
    @NotNull
    private Date postponement_end_date;

    public Postponement(@NotNull Long id, Integer postponement_id, String paragraph_of_document, Date postponement_start_date, Date postponement_end_date) {
        this.id = id;
        this.postponement_id = postponement_id;
        this.paragraph_of_document = paragraph_of_document;
        this.postponement_start_date = postponement_start_date;
        this.postponement_end_date = postponement_end_date;
    }

    public Postponement() {
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public Integer getPostponement_id() {
        return postponement_id;
    }

    public void setPostponement_id(Integer postponement_id) {
        this.postponement_id = postponement_id;
    }

    public String getParagraph_of_document() {
        return paragraph_of_document;
    }

    public void setParagraph_of_document(String paragraph_of_document) {
        this.paragraph_of_document = paragraph_of_document;
    }

    public Date getPostponement_start_date() {
        return postponement_start_date;
    }

    public void setPostponement_start_date(Date postponement_start_date) {
        this.postponement_start_date = postponement_start_date;
    }

    public Date getPostponement_end_date() {
        return postponement_end_date;
    }

    public void setPostponement_end_date(Date postponement_end_date) {
        this.postponement_end_date = postponement_end_date;
    }
}

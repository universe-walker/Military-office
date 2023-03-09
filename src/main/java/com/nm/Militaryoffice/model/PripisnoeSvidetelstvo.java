package com.nm.Militaryoffice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PripisnoeSvidetelstvo {
    @Size(max = 9)
    @NotEmpty
    private String seriesAndNumber;
    @Size(min = 2, max = 2)
    @NotEmpty
    private String healthCategory;
    @Size(max = 300)
    @NotEmpty
    private String whoIssued;
    @Size(max = 100)
    @NotEmpty
    private String commissionerFullname;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy-MM-dd"})
    private Date issueDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"yyyy-MM-dd"})
    private Date dateOfAppearance;

    public PripisnoeSvidetelstvo(String seriesAndNumber,
                                 String healthCategory,
                                 String whoIssued,
                                 String commissionerFullname,
                                 Date issueDate,
                                 Date dateOfAppearance) {
        this.seriesAndNumber = seriesAndNumber;
        this.healthCategory = healthCategory;
        this.whoIssued = whoIssued;
        this.commissionerFullname = commissionerFullname;
        this.issueDate = issueDate;
        this.dateOfAppearance = dateOfAppearance;
    }

    public PripisnoeSvidetelstvo() {
    }

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        this.seriesAndNumber = seriesAndNumber;
    }

    public String getHealthCategory() {
        return healthCategory;
    }

    public void setHealthCategory(String healthCategory) {
        this.healthCategory = healthCategory;
    }

    public String getWhoIssued() {
        return whoIssued;
    }

    public void setWhoIssued(String whoIssued) {
        this.whoIssued = whoIssued;
    }

    public String getCommissionerFullname() {
        return commissionerFullname;
    }

    public void setCommissionerFullname(String commissionerFullname) {
        this.commissionerFullname = commissionerFullname;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDateOfAppearance() {
        return dateOfAppearance;
    }

    public void setDateOfAppearance(Date dateOfAppearance) {
        this.dateOfAppearance = dateOfAppearance;
    }
}

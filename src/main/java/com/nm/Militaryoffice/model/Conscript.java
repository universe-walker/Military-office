package com.nm.Militaryoffice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class Conscript {
    private Long id;
    @Size(max = 9)
    private String seriesAndNumber;
    @NotEmpty
    @Size(min = 10, max = 10)
    private String passport;
    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;
    @Size(max = 30)
    private String patronymic;
    @NotEmpty
    @Size(max = 50)
    private String surname;
    @NotEmpty
    @Size(min = 1, max = 1)
    private String gender;
    @NotNull
    @Past
    private Date dateOfBirth;
    @NotEmpty
    @Size(max = 300)
    private String placeOfBirth;
    @NotEmpty
    @Size(max = 300)
    private String placeOfResidence;
    @Size(max = 70)
    private String occupation;
    @NotEmpty
    @Size(max = 10)
    private String maritalStatus;
    @Size(max = 70)
    private String sportsCategory;

    public Conscript() {
    }

    public Conscript(String seriesAndNumber,
                     String passport,
                     String name,
                     String patronymic,
                     String surname,
                     String gender,
                     Date dateOfBirth,
                     String placeOfBirth,
                     String placeOfResidence,
                     String occupation,
                     String maritalStatus,
                     String sportsCategory) {
        this.seriesAndNumber = seriesAndNumber;
        this.passport = passport;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.placeOfResidence = placeOfResidence;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.sportsCategory = sportsCategory;
    }

    public String getFullname() {
        return name + " " + patronymic + " " + surname;
    }

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        this.seriesAndNumber = seriesAndNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSportsCategory() {
        return sportsCategory;
    }

    public void setSportsCategory(String sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

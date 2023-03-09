package com.nm.Militaryoffice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class Education {
    private Long id_education;
    @NotNull
    private Long id;
    @NotEmpty(message = "строка \'уровень образования\' не должна быть пустой")
    @Size(max=30)
    private String education_level;
    @NotEmpty(message = "строка \'место\' не должна быть пустой")
    private String location;
    @NotNull(message = "строка 'дата начала обучения' не должна быть пустой")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy.MM.dd", "yyyy-MM-dd"})
    private Date edu_start_date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy.MM.dd", "yyyy-MM-dd"})
    private Date edu_end_date;

    public Long getId_education() {
        return id_education;
    }

    public Long getId() {
        return id;
    }

    public String getEducation_level() {
        return education_level;
    }

    public String getLocation() {
        return location;
    }

    public Date getEdu_start_date() {
        return edu_start_date;
    }

    public Date getEdu_end_date() {
        return edu_end_date;
    }

    public Education(
            Long id_education,
            Long id,
            String education_level,
            String location,
            Date edu_start_date,
            Date edu_end_date
    ) {
        this.id_education = id_education;
        this.id = id;
        this.education_level = education_level;
        this.location = location;
        this.edu_start_date = edu_start_date;
        this.edu_end_date = edu_end_date;
    }

    public Education() {
    }

    public void setId_education(Long id_education) {
        this.id_education = id_education;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEdu_start_date(Date edu_start_date) {
        this.edu_start_date = edu_start_date;
    }

    public void setEdu_end_date(Date edu_end_date) {
        this.edu_end_date = edu_end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(id_education, education.id_education) && id.equals(education.id) && education_level.equals(education.education_level) && location.equals(education.location) && edu_start_date.equals(education.edu_start_date) && Objects.equals(edu_end_date, education.edu_end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_education, id, education_level, location, edu_start_date, edu_end_date);
    }
}

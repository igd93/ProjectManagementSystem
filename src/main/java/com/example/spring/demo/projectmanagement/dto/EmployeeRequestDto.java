package com.example.spring.demo.projectmanagement.dto;

import com.example.spring.demo.projectmanagement.constraints.BirthDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeRequestDto {

    @NotBlank(message = "name should be specified")
    private String name;

    @NotBlank(message = "Family Name should be specified")
    private String familyName;


    @Past(message = "Date of birth cannot be set in the future")
    @DateTimeFormat
    @NotNull(message = "The date of birth is required")
    @BirthDate(message = "The employee must be 18 or older")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    //initialize in constructor
    private Set<Long> projects;

    public EmployeeRequestDto() {
        projects = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProjects(Set<Long> projects) {
        this.projects = projects;
    }

    public Set<Long> getProjects() {
        return projects;
    }
}

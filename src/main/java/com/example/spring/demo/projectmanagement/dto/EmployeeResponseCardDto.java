package com.example.spring.demo.projectmanagement.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeResponseCardDto {

    //Should have a derived class in this package

    private Long id;
    private String name;
    private String familyName;

    private Date dateOfBirth;

    private List<ProjectResponseDto> projects;

    public EmployeeResponseCardDto() {
        projects = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProjectResponseDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponseDto> projects) {
        this.projects = projects;
    }
}

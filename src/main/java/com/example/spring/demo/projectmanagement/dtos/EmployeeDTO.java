package com.example.spring.demo.projectmanagement.dtos;

import com.example.spring.demo.projectmanagement.entities.Project;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    private int id;
    private String name;
    private String familyName;

    private Date dateOfBirth;

    private List<Project> projectsDTO = new ArrayList<>();

    public EmployeeDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Project> getProjects() {
        return projectsDTO;
    }

    public void setProjects(List<Project> projectsDTO) {
        this.projectsDTO = projectsDTO;
    }
}

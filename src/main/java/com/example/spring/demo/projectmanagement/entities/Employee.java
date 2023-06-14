package com.example.spring.demo.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String familyName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
        name = "projEmp",
        joinColumns = @JoinColumn(name = "employeeId"),
        inverseJoinColumns =@JoinColumn(name = "projectId")
    )
    @JsonIgnoreProperties("employeeList")
    private List<Project> projects;// list of projects an employee is assigned to

    public Employee() {
         projects = new ArrayList<>();
    }

    public Employee(String name, String familyName, Date dateOfBirth) {
        this.name = name;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(String name, String familyName, Date dateOfBirth, List<Project> projects) {
        super();
        this.name = name;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
        project.getEmployeeList().add(this);
    }

    public void removeProject(Project project) {
        if (projects != null) projects.remove(project);
        project.getEmployeeList().remove(this);
    }

    @Override
    public String toString() {
        return "Employee[id = " + id + " ,name= " + name + " ,familyName = "
                + familyName + ", dateOfBirth= " + dateOfBirth + "project = " + projects;
    }
}

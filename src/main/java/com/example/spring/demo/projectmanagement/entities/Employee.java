package com.example.spring.demo.projectmanagement.entities;

import com.fasterxml.jackson.annotation.*;
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
    private int id;
    private String name;
    private String familyName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
        name = "proj_emp_relations",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns =@JoinColumn(name = "project_id")
    )
    @JsonIgnoreProperties("employeeList")
    private List<Project> projects = new ArrayList<>(); // list of projects an employee is assigned to

    public Employee() {

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

package com.example.spring.demo.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST},
    fetch = FetchType.LAZY)
//    @ManyToMany(mappedBy = "projects")
    @JsonIgnoreProperties("projects")
    private List<Employee> employeeList = new ArrayList<>();

    public Project() {

    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public Long getId() {
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employee.getProjects().add(this);

    }

    public void removeEmployee(Employee employee) {

        if (employeeList != null ) employeeList.remove(employee);
        employee.getProjects().remove(this);
    }

    @Override
    public String toString() {
        return "Project[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeList=" + employeeList +
                ']';
    }
}

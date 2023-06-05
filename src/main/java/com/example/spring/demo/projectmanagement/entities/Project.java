package com.example.spring.demo.projectmanagement.entities;

import com.example.spring.demo.projectmanagement.entities.Employee;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {

        if (employeeList != null ) employeeList.remove(employee);
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

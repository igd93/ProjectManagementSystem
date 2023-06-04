package com.example.spring.demo.projectmanagement.entities;

import com.example.spring.demo.projectmanagement.entities.Employee;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employeeList;

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
        employeeList.remove(employee);
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

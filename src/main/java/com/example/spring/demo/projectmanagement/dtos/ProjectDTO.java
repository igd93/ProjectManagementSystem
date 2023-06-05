package com.example.spring.demo.projectmanagement.dtos;

import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {

    private int id;
    private String name;

    List<EmployeeDTO> employeeDTOs = new ArrayList<>();

    public ProjectDTO() {

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

    public List<EmployeeDTO> getEmployeeList() {
        return employeeDTOs;
    }

    public void setEmployeeList(List<EmployeeDTO> employeeDTOs) {
        this.employeeDTOs = employeeDTOs;
    }
}

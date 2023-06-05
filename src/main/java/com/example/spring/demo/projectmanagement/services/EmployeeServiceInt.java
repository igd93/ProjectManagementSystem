package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dtos.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface EmployeeServiceInt {

    List<EmployeeDTO> allEmployees();

    Employee getEmployee(int id);

    Employee addEmployee(Employee employee);

    Employee addProject(int id, Project project);

    Employee removeProject(int id, Project project);

    void deleteEmployee(int id);
}
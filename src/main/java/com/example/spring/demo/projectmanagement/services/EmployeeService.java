package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> allEmployees();

    EmployeeDTO getEmployeeDTO(int id);

    Employee getEmployee(int id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee updatedEmployee);

    Employee addProject(int id, Project project);

    Employee removeProject(int id, Project project);

    void deleteEmployee(int id);
}

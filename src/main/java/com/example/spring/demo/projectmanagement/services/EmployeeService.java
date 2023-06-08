package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDTO> allEmployees();

    EmployeeResponseDTO getEmployeeDTO(int id);

    Employee getEmployee(int id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee updatedEmployee);

    Employee addProject(int id, Project project);

    Employee removeProject(int id, Project project);

    void deleteEmployee(int id);
}

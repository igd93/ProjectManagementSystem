package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseCardDTO> allEmployees();

    EmployeeResponseIdDTO getEmployeeDTO(Long id);

    Employee getEmployee(Long id);

    EmployeeResponseIdDTO addEmployee(EmployeeRequestDTO employee);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    void linkProject(Long employeeId, Long projectId);

    void unlinkProject(Long id, Project project);

    void deleteEmployee(Long id);
}

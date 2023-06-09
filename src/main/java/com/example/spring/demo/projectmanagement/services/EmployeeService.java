package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDTO> allEmployees();

    EmployeeResponseCardDTO getEmployee(Long id);

    EmployeeResponseIdDTO addEmployee(EmployeeRequestDTO employee);

    EmployeeResponseCardDTO updateEmployee(Long id, EmployeeRequestDTO updatedEmployee);

    void linkProject(Long employeeId, Long projectId);

    void unlinkProject(Long id, Long projectId);

    void deleteEmployee(Long id);
}

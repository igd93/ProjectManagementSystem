package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDto> allEmployees();

    EmployeeResponseCardDto getEmployee(Long id);

    EmployeeResponseIdDto addEmployee(EmployeeRequestDto employee);

    void updateEmployee(Long id, EmployeeRequestDto updatedEmployee);

    void linkProject(Long employeeId, Long projectId);

    void unlinkProject(Long id, Long projectId);

    void deleteEmployee(Long id);
}

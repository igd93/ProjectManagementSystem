package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO entityToDTO(Employee employee) {

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setFamilyName(employee.getFamilyName());
        employeeResponseDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeResponseDTO.setProjects(employee.getProjects());

        return employeeResponseDTO;
    }

    public  List<EmployeeResponseDTO> entityToDTO(List<Employee> employees) {
        return employees.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Employee dTOToEntity(EmployeeResponseDTO employeeResponseDTO) {
        Employee employee = new Employee();
        employee.setId(employeeResponseDTO.getId());
        employee.setName(employeeResponseDTO.getName());
        employee.setFamilyName(employeeResponseDTO.getFamilyName());
        employee.setDateOfBirth(employeeResponseDTO.getDateOfBirth());
        employee.setProjects(employeeResponseDTO.getProjects());
        return employee;
    }

    public List<Employee> dTOToEntity(List<EmployeeResponseDTO> employeeResponseDTOList) {
        return employeeResponseDTOList.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }


}

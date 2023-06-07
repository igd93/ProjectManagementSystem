package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDTO entityToDTO(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setFamilyName(employee.getFamilyName());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setProjects(employee.getProjects());

        return employeeDTO;
    }

    public  List<EmployeeDTO> entityToDTO(List<Employee> employees) {
        return employees.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Employee dTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setFamilyName(employeeDTO.getFamilyName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setProjects(employeeDTO.getProjects());
        return employee;
    }

    public List<Employee> dTOToEntity(List<EmployeeDTO> employeeDTOList) {
        return employeeDTOList.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }


}

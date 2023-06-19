package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDto;
import com.example.spring.demo.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    private final ProjectMapper projectMapper;

    @Autowired
    public EmployeeMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }


    //general mapper for request and response
    public EmployeeResponseDto entityToDTO(Employee employee) {
        EmployeeResponseDto employeeResponseDTO = new EmployeeResponseDto();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setFamilyName(employee.getFamilyName());
        employeeResponseDTO.setDateOfBirth(employee.getDateOfBirth());
        return employeeResponseDTO;
    }

    public List<EmployeeResponseDto> entityToDTO(List<Employee> employees) {
        return  employees.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public EmployeeResponseCardDto entityToCardDTO(Employee employee) {
        EmployeeResponseCardDto employeeResponseCardDTO = new EmployeeResponseCardDto();
        employeeResponseCardDTO.setId(employee.getId());
        employeeResponseCardDTO.setName(employee.getName());
        employeeResponseCardDTO.setFamilyName(employee.getFamilyName());
        employeeResponseCardDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeResponseCardDTO.setProjects(projectMapper.entityToDTO(employee.getProjects()));
        return employeeResponseCardDTO;
    }

    public EmployeeResponseIdDto employeeToId(Employee employee) {
        EmployeeResponseIdDto employeeResponseIdDTO = new EmployeeResponseIdDto();
        employeeResponseIdDTO.setId(employee.getId());
        return employeeResponseIdDTO;
    }

    public Employee dTOToEntity(EmployeeRequestDto employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setFamilyName(employeeRequestDTO.getFamilyName());
        employee.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
        return employee;
    }



//    public List<Employee> dTOToEntity(List<EmployeeResponseDTO> employeeResponseDTOList) {
//        return employeeResponseDTOList.stream().map(this::dTOToEntity).collect(Collectors.toList());
//    }


}

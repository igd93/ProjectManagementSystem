package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import com.example.spring.demo.projectmanagement.services.ProjectServiceImp;
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
    public EmployeeResponseDTO entityToDTO(Employee employee) {

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setFamilyName(employee.getFamilyName());
        employeeResponseDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeResponseDTO.setProjects(projectMapper.entityToDTO(employee.getProjects()));

        return employeeResponseDTO;
    }

    public EmployeeResponseCardDTO entityToCardDTO(Employee employee) {
        EmployeeResponseCardDTO employeeResponseCardDTO = new EmployeeResponseCardDTO();
        employeeResponseCardDTO.setId(employee.getId());
        employeeResponseCardDTO.setName(employee.getName());
        employeeResponseCardDTO.setFamilyName(employee.getFamilyName());
        employeeResponseCardDTO.setDateOfBirth(employee.getDateOfBirth());
        return employeeResponseCardDTO;
    }

    public EmployeeResponseIdDTO employeeToId(Employee employee) {
        EmployeeResponseIdDTO employeeResponseIdDTO = new EmployeeResponseIdDTO();
        employeeResponseIdDTO.setId(employee.getId());
        return employeeResponseIdDTO;
    }


    public List<EmployeeResponseCardDTO> entityToCardDTO(List<Employee> employees) {
        return  employees.stream().map(this::entityToCardDTO).collect(Collectors.toList());
    }

    public Employee dTOToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setFamilyName(employeeRequestDTO.getFamilyName());
        employee.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
        List<Long> projectIds = employeeRequestDTO.getProjects();
        if (!projectIds.isEmpty()) {
            employee.setProjects(projectMapper.projectIdsToProject(projectIds));
        }
        return employee;
    }



//    public List<Employee> dTOToEntity(List<EmployeeResponseDTO> employeeResponseDTOList) {
//        return employeeResponseDTOList.stream().map(this::dTOToEntity).collect(Collectors.toList());
//    }


}

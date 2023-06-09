package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.EmployeeMapper;
import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    private final EmployeeMapper employeeMapper;


    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository,
                              EmployeeMapper employeeMapper,
                              ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.projectRepository = projectRepository;

    }

    @Override
    public List<EmployeeResponseDTO> allEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.entityToDTO(employees);
    }

    @Override
    public EmployeeResponseCardDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with such id " + id + " does not exist"));
        return employeeMapper.entityToCardDTO(employee);
    }

    @Override
    public EmployeeResponseIdDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeMapper.dTOToEntity(employeeRequestDTO);
        List<Long> projectIds = employeeRequestDTO.getProjects();
        if(!CollectionUtils.isEmpty(projectIds)) {
            List<Project> projects = projectRepository.findAllById(projectIds);
            if (projects.size() != projectIds.size())
                projectIds.remove(projects.stream().map(Project::getId).toList());
            employee.setProjects();
        }
        Long employeeId = employeeRepository.save(employee).getId();
        return new EmployeeResponseIdDTO(employeeId);
    }

    @Override
    public EmployeeResponseCardDTO updateEmployee(Long id, EmployeeRequestDTO updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (updatedEmployee.getName() != null) employee.setName(updatedEmployee.getName());
            if (updatedEmployee.getFamilyName() != null) employee.setFamilyName(updatedEmployee.getFamilyName());
            if (updatedEmployee.getDateOfBirth() != null) employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
            List<Long> projectIds = updatedEmployee.getProjects();
            if(!projectIds.isEmpty()) {
                employee.setProjects(projectRepository.findAllById(projectIds));
            }
            Employee savedEmployee = employeeRepository.save(employee);
            return employeeMapper.entityToCardDTO(savedEmployee);
        }
        else {
            throw new RuntimeException("The employee with id " + id + " cannot be updated as it does not exist");
        }
    }

    @Override
    public void linkProject(Long employeeId, Long projectId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalEmployee.isPresent() && optionalProject.isPresent()) {
            Employee employee = optionalEmployee.get();
            Project project = optionalProject.get();
            employee.addProject(project);
            employeeRepository.save(employee);
        }
        else {
            throw new RuntimeException("Employee with id " + employeeId + "or project with id "
                    + projectId + " does not exist");
        }
    }

    @Override
    public void unlinkProject(Long employeeId, Long projectId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalEmployee.isPresent() && optionalProject.isPresent()) {
            Employee employee = optionalEmployee.get();
            Project project = optionalProject.get();
            employee.removeProject(project);
            employeeRepository.save(employee);
        }
        else {
            throw new RuntimeException("Employee with id " + employeeId + "or project with id "
                    + projectId + " does not exist");
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }



}

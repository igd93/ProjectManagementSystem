package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDto;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.EmployeeMapper;
import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public List<EmployeeResponseDto> allEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.entityToDTO(employees);
    }

    @Override
    public EmployeeResponseCardDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with such id "
                        + id + " does not exist"));
        return employeeMapper.entityToCardDTO(employee);
    }

    @Override
    public EmployeeResponseIdDto addEmployee(EmployeeRequestDto employeeRequestDTO) {
        Employee employee = employeeMapper.dTOToEntity(employeeRequestDTO);
        //convert list to set, to avoid dups
        List<Long> projectIds = employeeRequestDTO.getProjects();
        if(!CollectionUtils.isEmpty(projectIds)) {
            List<Project> projects = projectRepository.findAllById(projectIds);
            if (projects.size() != projectIds.size()) {
                //should not operate on a DTO list, create a new one (via stream)
                projectIds.removeAll(projects.stream().map(Project::getId).toList());
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Records with the ids "
                        + projectIds + " do not exist");
            }
            else {
                employee.setProjects(projects);
            }
        }
        Long employeeId = employeeRepository.save(employee).getId();
        return new EmployeeResponseIdDto(employeeId);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequestDto updatedEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee with id "
                        + id + " cannot be updated as it does not exist"));
        if (updatedEmployee.getName() != null)
            employee.setName(updatedEmployee.getName());
        if (updatedEmployee.getFamilyName() != null)
            employee.setFamilyName(updatedEmployee.getFamilyName());
        if (updatedEmployee.getDateOfBirth() != null)
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        List<Long> projectIds = updatedEmployee.getProjects();
        if (!CollectionUtils.isEmpty(projectIds)) {
            List<Project> projects = projectRepository.findAllById(projectIds);
            if (projects.size() != projectIds.size()) {
                projectIds.removeAll(projects.stream().map(Project::getId).toList());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Records with the ids "
                        + projectIds + " do not exist");
            } else {
                employee.setProjects(projects);
            }
        }
        employeeRepository.save(employee);
    }

    @Override
    public void linkProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id"
                        + employeeId + " does not exist"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id"
                        + projectId + "does not exist"));
        employee.addProject(project);
        employeeRepository.save(employee);
    }

    @Override
    public void unlinkProject(Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id"
                        + employeeId + " does not exist"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id"
                        + projectId + "does not exist"));
        employee.removeProject(project);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        //check if the id exists
        employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id " + id + "dos not exist"));
        employeeRepository.deleteById(id);
    }

}

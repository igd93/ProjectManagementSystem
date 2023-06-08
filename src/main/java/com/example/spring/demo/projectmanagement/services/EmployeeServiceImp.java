package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.EmployeeMapper;
import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<EmployeeResponseCardDTO> allEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.entityToCardDTO(employees);
    }

    @Override
    public EmployeeResponseIdDTO getEmployeeDTO(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return employeeMapper.employeeToId(employee);
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }

    }

    @Override
    public EmployeeResponseIdDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeMapper.dTOToEntity(employeeRequestDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponseIdDTO id = new EmployeeResponseIdDTO();
        id.setId(savedEmployee.getId());
        return id;
    }

    @Override
    public EmployeeResponseCardDTO updateEmployee(Long id, EmployeeRequestDTO updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setFamilyName(updatedEmployee.getFamilyName());
            List<Long> projectIds = updatedEmployee.getProjects();
            List<Project> projects =
            employee.setProjects(updatedEmployee.getProjects());
            return employeeRepository.save(employee);
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

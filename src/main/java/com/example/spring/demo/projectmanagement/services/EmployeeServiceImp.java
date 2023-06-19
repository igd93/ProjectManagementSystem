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
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    private final EmployeeMapper employeeMapper;

    private final SmartValidator validator;


    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository,
                              EmployeeMapper employeeMapper,
                              ProjectRepository projectRepository,
                              SmartValidator validator) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.projectRepository = projectRepository;
        this.validator = validator;

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
        Set<Long> projectIds = employeeRequestDTO.getProjects();
        if(!CollectionUtils.isEmpty(projectIds)) {
            Set<Project> projects = new HashSet<>(projectRepository.findAllById(projectIds));
            if (projects.size() != projectIds.size()) {
                Set<Long> correctIds = projects.stream()
                        .map(Project::getId)
                        .collect(Collectors.toSet());
                Set<Long> incorrectIds = projectIds.stream().filter(projectId -> !correctIds.contains(projectId))
                        .collect(Collectors.toSet());
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Records with the ids "
                        + incorrectIds + " do not exist");
            }
            else {
                employee.setProjects(projects);
            }
        }
        Long employeeId = employeeRepository.save(employee).getId();
        return new EmployeeResponseIdDto(employeeId);
    }

    @Override
    public void updateEmployee(Long id, Map<String, Object> changes) throws ResponseStatusException{
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee with id "
                + id + " cannot be updated as it does not exist"));
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        WebDataBinder binder = new WebDataBinder(employeeRequestDto);
        BindingResult bindingResult = binder.getBindingResult();
        binder.bind(new MutablePropertyValues(changes));
        changes.forEach((k, v) -> validator.validateValue(EmployeeRequestDto.class, k, v, bindingResult));
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }
        if (employeeRequestDto.getName() != null)
            employee.setName(employeeRequestDto.getName());
        if (employeeRequestDto.getFamilyName() != null)
            employee.setFamilyName(employeeRequestDto.getFamilyName());
        if (employeeRequestDto.getDateOfBirth() != null)
            employee.setDateOfBirth(employeeRequestDto.getDateOfBirth());
        Set<Long> projectIds = employeeRequestDto.getProjects();
        if (!CollectionUtils.isEmpty(projectIds)) {
            Set<Project> projects = new HashSet<>(projectRepository.findAllById(projectIds));
            if (projects.size() != projectIds.size()) {
                Set<Long> correctIds = projects.stream()
                        .map(Project::getId)
                        .collect(Collectors.toSet());
                Set<Long> incorrectIds = projectIds.stream().filter(projectId -> !correctIds.contains(projectId))
                        .collect(Collectors.toSet());
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Records with the ids "
                        + incorrectIds + " do not exist");
            } else {
                employee.setProjects(projects);
            }
        }
        employeeRepository.save(employee);
    }

//    @Override
//    public void updateEmployee(Long id, EmployeeRequestDto updatedEmployee) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee with id "
//                        + id + " cannot be updated as it does not exist"));
//        if (updatedEmployee.getName() != null)
//            employee.setName(updatedEmployee.getName());
//        if (updatedEmployee.getFamilyName() != null)
//            employee.setFamilyName(updatedEmployee.getFamilyName());
//        if (updatedEmployee.getDateOfBirth() != null)
//            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
//        Set<Long> projectIds = updatedEmployee.getProjects();
//        if (!CollectionUtils.isEmpty(projectIds)) {
//            Set<Project> projects = new HashSet<>(projectRepository.findAllById(projectIds));
//            if (projects.size() != projectIds.size()) {
//                Set<Long> correctIds = projects.stream()
//                        .map(Project::getId)
//                        .collect(Collectors.toSet());
//                Set<Long> incorrectIds = projectIds.stream().filter(projectId -> !correctIds.contains(projectId))
//                        .collect(Collectors.toSet());
//                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Records with the ids "
//                        + incorrectIds + " do not exist");
//            } else {
//                employee.setProjects(projects);
//            }
//        }
//        employeeRepository.save(employee);
//    }

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
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id "
                                + id + " does not exist"));
        employeeRepository.deleteById(id);
    }

}

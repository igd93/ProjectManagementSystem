package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.EmployeeMapper;
import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {


    private final EmployeeRepository repo;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository repo, EmployeeMapper employeeMapper) {
        this.repo = repo;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> allEmployees() {
        List<Employee> employees = repo.findAll();
        return employeeMapper.entityToDTO(employees);
    }

    @Override
    public EmployeeDTO getEmployeeDTO(int id) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return employeeMapper.entityToDTO(employee);
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }
    }

    @Override
    public Employee getEmployee(int id) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }

    }

    @Override
    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }
    @Override
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setFamilyName(updatedEmployee.getFamilyName());
            employee.setProjects(updatedEmployee.getProjects());
            return repo.save(employee);
        }
        else {
            throw new RuntimeException("The employee with id " + id + " cannot be updated as it does not exist");
        }
    }

    @Override
    public Employee addProject(int id, Project project) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.addProject(project);
            return repo.save(employee);
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }
    }

    @Override
    public Employee removeProject(int id, Project project) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.removeProject(project);
            return repo.save(employee);
        }
        else {
            throw new RuntimeException("Employee with such id " + id + " does not exist");
        }
    }

    @Override
    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }



}

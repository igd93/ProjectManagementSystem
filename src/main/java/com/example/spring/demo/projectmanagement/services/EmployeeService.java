package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repo;

    public List<Employee> allEmployees() {
        return repo.findAll();
    }

    public Employee getEmployee(int id) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee with such id" + id + "does not exist");
        }
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(0);
        return repo.save(employee);
    }

    public Employee addProject(int id, Project project) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.addProject(project);
            return repo.save(employee);
        }
        else {
            throw new RuntimeException("Employee with such id" + id + "does not exist");
        }
    }

    public Employee removeProject(int id, Project project) {
        Optional<Employee> optionalEmployee = repo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.removeProject(project);
            return repo.save(employee);
        }
        else {
            throw new RuntimeException("Employee with such id " + id + "does not exist");
        }
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }



}

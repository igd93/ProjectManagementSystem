package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dtos.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<EmployeeDTO> allEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}/projects/{project_id}")
    public Employee addProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectService.getProject(project_id);
        return employeeService.addProject(id, project);
    }

    @PutMapping("/{id}/remove_projects/{project_id}")
    public Employee removeProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectService.getProject(project_id);
        return employeeService.removeProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }



}

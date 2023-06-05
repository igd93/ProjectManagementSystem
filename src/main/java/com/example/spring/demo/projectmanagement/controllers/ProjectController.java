package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dtos.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<ProjectDTO> allProjects() {
        return projectService.allProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable int id) {
        return projectService.getProject(id);
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping("/{id}/employees/{employee_id}")
    public Project addEmployee(@PathVariable int id, @PathVariable int employee_id) {
        Employee employee = employeeService.getEmployee(employee_id);
        return projectService.addEmployee(id, employee);
    }

    @PutMapping("/{id}/remove_employees/{employee_id}")
    public Project removeEmployee(@PathVariable int id, @PathVariable int employee_id) {
        Employee employee = employeeService.getEmployee(employee_id);
        return projectService.removeEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.removeProject(id);
    }


}

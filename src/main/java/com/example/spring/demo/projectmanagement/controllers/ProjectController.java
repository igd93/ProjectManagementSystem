package com.example.spring.demo.projectmanagement.controllers;


import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
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

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<ProjectResponseDTO> allProjects() {
        return projectService.allProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectDTO(@PathVariable int id) {
        return projectService.getProjectDTO(id);
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PostMapping("/{id}/employees/{employeeId}")
    public Project addEmployee(@PathVariable int id, @PathVariable int employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return projectService.addEmployee(id, employee);
    }

    @PostMapping("/{id}/removeEmployees/{employeeId}")
    public Project removeEmployee(@PathVariable int id, @PathVariable int employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return projectService.removeEmployee(id, employee);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.removeProject(id);
    }


}

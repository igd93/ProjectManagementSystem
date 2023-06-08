package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping
    public List<EmployeeResponseDTO> allEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeDTO(@PathVariable int id) {
        return employeeService.getEmployeeDTO(id);
    }


    //should return just id
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    // Unacceptable, should use POST
    @PostMapping ("/{id}/projects/{projectId}")
    public Employee addProject(@PathVariable int id, @PathVariable int projectId) {
        Project project = projectService.getProject(projectId);
        return employeeService.addProject(id, project);
    }

    //Camel case

    @PostMapping("/{id}/removeProjects/{projectId}")
    public Employee removeProject(@PathVariable int id, @PathVariable int projectId) {
        Project project = projectService.getProject(projectId);
        return employeeService.removeProject(id, project);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }



}

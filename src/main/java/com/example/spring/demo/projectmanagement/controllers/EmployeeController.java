package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.ProjectMapper;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import com.example.spring.demo.projectmanagement.services.EmployeeServiceImp;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import com.example.spring.demo.projectmanagement.services.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<EmployeeDTO> allEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeDTO(@PathVariable int id) {
        return employeeService.getEmployeeDTO(id);
    }


    //should return just id
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    // Unacceptable, should use POST
    @PostMapping ("/{id}/projects/{project_id}")
    public Employee addProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectService.getProject(project_id);
        return employeeService.addProject(id, project);
    }

    @PostMapping("/{id}/remove_projects/{project_id}")
    public Employee removeProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectService.getProject(project_id);
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

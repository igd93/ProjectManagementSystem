package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
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
    //TO DO
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Should be a separate DTO without the list of Projects
    @GetMapping
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeService.allEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable int id) {
        return employeeService.getEmployeeDTO(id);
    }


    //should return just customDTO with id in it
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequestDTO employee) {
        return employeeService.addEmployee(employee);
    }

    //ResponseEntity
    // Unacceptable, should use POST
    @PostMapping ("/{id}/projects/{projectId}/")
    public void linkProject(@PathVariable Long id, @PathVariable Long projectId) {
        //refactor to link
        //Project project = projectService.getProject(projectId);
        return employeeService.addProject(id, projectId);
    }

    //Camel case

    @DeleteMapping("/{id}/projects/{projectId}/")
    public void unlinkProject(@PathVariable Long id, @PathVariable Long projectId) {
        //re-factor to unlink
        //Project project = projectService.getProject(projectId);
        return employeeService.removeProject(id, projectId);
    }

    //Patch instead of Put
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }


    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }



}

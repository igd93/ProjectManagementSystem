package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
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
    public EmployeeResponseCardDTO getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }


    //should return just customDTO with id in it
    @PostMapping
    public EmployeeResponseIdDTO createEmployee(@RequestBody EmployeeRequestDTO employee) {
        return employeeService.addEmployee(employee);
    }

    //ResponseEntity
    // Unacceptable, should use POST
    @PostMapping ("/{id}/projects/{projectId}")
    public void linkProject(@PathVariable Long id, @PathVariable Long projectId) {
        employeeService.linkProject(id, projectId);
    }

    //Camel case

    @DeleteMapping("/{id}/projects/{projectId}")
    public void unlinkProject(@PathVariable Long id, @PathVariable Long projectId) {
        employeeService.unlinkProject(id, projectId);
    }

    //Patch instead of Put
    @PatchMapping("/{id}")
    public EmployeeResponseCardDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }


    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }



}

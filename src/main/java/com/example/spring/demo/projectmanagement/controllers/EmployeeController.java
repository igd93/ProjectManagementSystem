package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDTO;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDTO;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.allEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseCardDTO> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }


    //should return just customDTO with id in it
    @PostMapping
    public ResponseEntity<EmployeeResponseIdDTO> createEmployee(@RequestBody EmployeeRequestDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }

    //ResponseEntity
    // Unacceptable, should use POST
    @PostMapping ("/{id}/projects/{projectId}")
    public ResponseEntity<Void> linkProject(@PathVariable Long id, @PathVariable Long projectId) {
        employeeService.linkProject(id, projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Camel case

    @DeleteMapping("/{id}/projects/{projectId}")
    public ResponseEntity<Void> unlinkProject(@PathVariable Long id, @PathVariable Long projectId) {
        employeeService.unlinkProject(id, projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //No content 
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO updateEmployee) {
        employeeService.updateEmployee(id, updateEmployee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}

package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeRequestDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseCardDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseDto;
import com.example.spring.demo.projectmanagement.dto.EmployeeResponseIdDto;
import com.example.spring.demo.projectmanagement.services.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
        logger.info("Getting the list of all employees");
        return ResponseEntity.ok(employeeService.allEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseCardDto> getEmployee(@PathVariable Long id) {
        logger.info("Getting employee with an id {}", id);
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }



    @PostMapping
    public ResponseEntity<EmployeeResponseIdDto> createEmployee(@Valid @RequestBody EmployeeRequestDto employee) {
        logger.info("Creating an employee {}", employee.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }


    @PostMapping ("/{id}/projects/{projectId}")
    public ResponseEntity<Void> linkProject(@PathVariable Long id, @PathVariable Long projectId) {
        logger.info("Assigning an employee {} to a project {}", id, projectId);
        employeeService.linkProject(id, projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Camel case

    @DeleteMapping("/{id}/projects/{projectId}")
    public ResponseEntity<Void> unlinkProject(@PathVariable Long id, @PathVariable Long projectId) {
        logger.info("Unassigning an employee {} from a project {}", id, projectId);
        employeeService.unlinkProject(id, projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto updateEmployee) {
        logger.info("Updating the employee {} data", id);
        employeeService.updateEmployee(id, updateEmployee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id) {
        logger.info("Deleting an employee {} ", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

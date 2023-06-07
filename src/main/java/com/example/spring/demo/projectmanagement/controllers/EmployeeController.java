package com.example.spring.demo.projectmanagement.controllers;

import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.services.EmployeeServiceImp;
import com.example.spring.demo.projectmanagement.services.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeServiceImp employeeServiceImp;

    @Autowired
    ProjectServiceImp projectServiceImp;

    @GetMapping
    public List<EmployeeDTO> allEmployees() {
        return employeeServiceImp.allEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        return employeeServiceImp.getEmployee(id);
    }


    //should return just id
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeServiceImp.addEmployee(employee);
    }


    // Unacceptable, should use POST
    @PutMapping("/{id}/projects/{project_id}")
    public Employee addProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectServiceImp.getProject(project_id);
        return employeeServiceImp.addProject(id, project);
    }

    @PutMapping("/{id}/remove_projects/{project_id}")
    public Employee removeProject(@PathVariable int id, @PathVariable int project_id) {
        Project project = projectServiceImp.getProject(project_id);
        return employeeServiceImp.removeProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable int id) {
        employeeServiceImp.deleteEmployee(id);
    }



}

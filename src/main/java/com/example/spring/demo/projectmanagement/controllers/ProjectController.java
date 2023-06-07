package com.example.spring.demo.projectmanagement.controllers;


import com.example.spring.demo.projectmanagement.dto.EmployeeDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.EmployeeMapper;
import com.example.spring.demo.projectmanagement.services.EmployeeServiceImp;
import com.example.spring.demo.projectmanagement.services.ProjectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectServiceImp projectServiceImp;

    @Autowired
    EmployeeServiceImp employeeServiceImp;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<Project> allProjects() {
        return projectServiceImp.allProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable int id) {
        return projectServiceImp.getProject(id);
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectServiceImp.addProject(project);
    }

    @PutMapping("/{id}/employees/{employee_id}")
    public Project addEmployee(@PathVariable int id, @PathVariable int employee_id) {
        EmployeeDTO employeeDTO = employeeServiceImp.getEmployee(employee_id);
        Employee employee = employeeMapper.dTOToEntity(employeeDTO);
        return projectServiceImp.addEmployee(id, employee);
    }

    @PutMapping("/{id}/remove_employees/{employee_id}")
    public Project removeEmployee(@PathVariable int id, @PathVariable int employee_id) {
        EmployeeDTO employeeDTO = employeeServiceImp.getEmployee(employee_id);
        Employee employee = employeeMapper.dTOToEntity(employeeDTO);
        return projectServiceImp.removeEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectServiceImp.removeProject(id);
    }


}

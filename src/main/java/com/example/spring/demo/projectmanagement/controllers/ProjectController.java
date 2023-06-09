package com.example.spring.demo.projectmanagement.controllers;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDTO;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponseDTO> allProjects() {
        return projectService.allProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponseDTO getProjectDTO(@PathVariable Long id) {
        return projectService.getProjectDTO(id);
    }

    @PostMapping
    public ProjectResponseIdDTO createProject(@RequestBody ProjectRequestDTO project) {
        return projectService.createProject(project);
    }

//    @PostMapping("/{id}/employees/{employeeId}")
//    public void assignEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
//        projectService.assignEmployee(projectId, employeeId);
//    }

//    @DeleteMapping("/{id}/employees/{employeeId}")
//    public void unassignEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
//        projectService.unassignEmployee(projectId, employeeId);
//    }

//    @PutMapping("/{id}")
//    public void updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO updatedProject) {
//        projectService.updateProject(id, updatedProject);
//    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.removeProject(id);
    }


}

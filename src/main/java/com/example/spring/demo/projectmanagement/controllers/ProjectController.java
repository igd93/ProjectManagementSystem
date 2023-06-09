package com.example.spring.demo.projectmanagement.controllers;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDTO;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProjectResponseDTO>> getProjects() {
        List<ProjectResponseDTO> projectResponseDTOS = projectService.allProjects();
        return ResponseEntity.ok(projectResponseDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable Long id) {
        ProjectResponseDTO projectResponseDTO = projectService.getProjectDTO(id);
        return ResponseEntity.ok(projectResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseIdDTO> createProject(@RequestBody ProjectRequestDTO project) {
        ProjectResponseIdDTO projectResponseIdDTO = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseIdDTO);
    }

//    @PostMapping("/{id}/employees/{employeeId}")
//    public void assignEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
//        projectService.assignEmployee(projectId, employeeId);
//    }

//    @DeleteMapping("/{id}/employees/{employeeId}")
//    public void unassignEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
//        projectService.unassignEmployee(projectId, employeeId);
//    }

    // Fix to NoContent and void body
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO updatedProject) {
       ProjectResponseDTO projectResponseDTO =  projectService.updateProject(id, updatedProject);
       return ResponseEntity.ok(projectResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.removeProject(id);
        return ResponseEntity.noContent().build();
    }


}

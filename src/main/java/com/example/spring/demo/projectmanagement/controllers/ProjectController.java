package com.example.spring.demo.projectmanagement.controllers;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDto;
import com.example.spring.demo.projectmanagement.services.ProjectService;
import com.example.spring.demo.projectmanagement.services.ProjectServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(ProjectServiceImp.class);

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        logger.info("Getting the list of all projects");
        List<ProjectResponseDto> projectResponseDtos = projectService.allProjects();
        return ResponseEntity.ok(projectResponseDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) {
        logger.info("Getting the project with id {}", id);
        ProjectResponseDto projectResponseDTO = projectService.getProject(id);
        return ResponseEntity.ok(projectResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseIdDto> createProject( @Valid @RequestBody ProjectRequestDto project) {
        logger.info("Creating a project {} ", project.getName());
        ProjectResponseIdDto projectResponseIdDTO = projectService.createProject(project);
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
    public ResponseEntity<Void> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto updatedProject) {
       logger.info("Updating the project {} id", id);
       projectService.updateProject(id, updatedProject);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        logger.info("Deleting the project with {}", id);
        projectService.removeProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

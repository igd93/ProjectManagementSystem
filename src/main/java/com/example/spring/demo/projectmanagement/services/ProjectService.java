package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> projectIdsToProject(List<Long> projectIds);

    List<ProjectResponseDTO> allProjects();

    ProjectResponseDTO getProject(Long id);

    ProjectResponseIdDTO createProject(ProjectRequestDTO project);

    void updateProject(Long id, ProjectRequestDTO updateProject);

    //void assignEmployee(Long projectId, Long employeeId);

    //void unassignEmployee(Long projectId, Long employeeId);

    void removeProject(Long id);


}

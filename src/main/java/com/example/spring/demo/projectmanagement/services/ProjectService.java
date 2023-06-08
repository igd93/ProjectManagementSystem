package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> projectIdsToProject(List<Long> projectIds);

    List<ProjectResponseDTO> allProjects();

    ProjectResponseDTO getProjectDTO(Long id);

    Project getProject(Long id);

    Project addProject(Project project);

    Project updateProject(Long id, Project updateProject);

    Project addEmployee(Long id, Employee employee);

    Project removeEmployee(Long id, Employee employee);

    void removeProject(Long id);


}

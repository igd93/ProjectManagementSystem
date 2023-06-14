package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDto;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> projectIdsToProject(List<Long> projectIds);

    List<ProjectResponseDto> allProjects();

    ProjectResponseDto getProject(Long id);

    ProjectResponseIdDto createProject(ProjectRequestDto project);

    void updateProject(Long id, ProjectRequestDto updateProject);

    //void assignEmployee(Long projectId, Long employeeId);

    //void unassignEmployee(Long projectId, Long employeeId);

    void removeProject(Long id);


}

package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public ProjectResponseDTO entityToDTO(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        return projectResponseDTO;
    }

    public List<ProjectResponseDTO> entityToDTO(List<Project> projects) {
        return projects.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Project dTOToEntity(ProjectResponseDTO projectResponseDTO) {
        Project project = new Project();
        project.setId(projectResponseDTO.getId());
        project.setName(projectResponseDTO.getName());
        project.setEmployeeList(new ArrayList<Employee>());
        return project;
    }

    public List<Project> dtoToEntity(List<ProjectResponseDTO> projectResponseDTOS) {
        return projectResponseDTOS.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }
}

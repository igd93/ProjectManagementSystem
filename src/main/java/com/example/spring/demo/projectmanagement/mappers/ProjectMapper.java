package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public ProjectDTO entityToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
    }

    public List<ProjectDTO> entityToDTO(List<Project> projects) {
        return projects.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Project dTOToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setEmployeeList(new ArrayList<Employee>());
        return project;
    }

    public List<Project> dtoToEntity(List<ProjectDTO> projectDTOS) {
        return projectDTOS.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }
}

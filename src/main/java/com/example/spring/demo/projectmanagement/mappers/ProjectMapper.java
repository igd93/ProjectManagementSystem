package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.ProjectRequestDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDTO;
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



    public ProjectResponseIdDTO idToDTO(Project project) {
        ProjectResponseIdDTO projectResponseIdDTO = new ProjectResponseIdDTO();
        projectResponseIdDTO.setId(project.getId());
        return projectResponseIdDTO;
    }

    public List<ProjectResponseDTO> entityToDTO(List<Project> projects) {
        return projects.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Project dTOToEntity(ProjectRequestDTO projectRequestDTO) {
        Project project = new Project();
        project.setId(projectRequestDTO.getId());
        project.setName(projectRequestDTO.getName());
        project.setEmployeeList(new ArrayList<Employee>());
        return project;
    }

    public List<Project> dtoToEntity(List<ProjectRequestDTO> projectRequestDTOS) {
        return projectRequestDTOS.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }
}

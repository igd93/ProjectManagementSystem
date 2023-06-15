package com.example.spring.demo.projectmanagement.mappers;

import com.example.spring.demo.projectmanagement.dto.ProjectRequestDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDto;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {


    public ProjectResponseDto entityToDTO(Project project) {
        ProjectResponseDto projectResponseDTO = new ProjectResponseDto();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        return projectResponseDTO;
    }



    public ProjectResponseIdDto idToDTO(Project project) {
        ProjectResponseIdDto projectResponseIdDTO = new ProjectResponseIdDto();
        projectResponseIdDTO.setId(project.getId());
        return projectResponseIdDTO;
    }

    public List<ProjectResponseDto> entityToDTO(Set<Project> projects) {
        return projects.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Project dTOToEntity(ProjectRequestDto projectRequestDTO) {
        Project project = new Project();
        project.setId(projectRequestDTO.getId());
        project.setName(projectRequestDTO.getName());
        project.setEmployeeList(new ArrayList<Employee>());
        return project;
    }

    public List<Project> dtoToEntity(List<ProjectRequestDto> projectRequestDTOS) {
        return projectRequestDTOS.stream().map(this::dTOToEntity).collect(Collectors.toList());
    }
}

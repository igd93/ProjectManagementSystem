package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.dtos.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {


    public ProjectDTO entityToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(projectDTO.getName());
        //projectDTO.setEmployeeList(EmployeeMapper.entityToDTO(project.getEmployeeList()));
        return projectDTO;
    }

    public List<ProjectDTO> entityToDTO(List<Project> projects) {
        return projects.stream().map(x-> entityToDTO(x)).collect(Collectors.toList());
    }

    public Project dTOToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        //project.setEmployeeList(EmployeeMapper.dTOToEntity(projectDTO.getEmployeeList()));
        return project;
    }

    public List<Project> dTOToEntity(List<ProjectDTO> projectDTOList) {
        return projectDTOList.stream().map(x-> dTOToEntity(x)).collect(Collectors.toList());
    }
}

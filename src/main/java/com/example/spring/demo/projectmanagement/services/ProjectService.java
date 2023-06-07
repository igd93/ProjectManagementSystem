package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> allProjects();

    ProjectDTO getProjectDTO(int id);

    Project getProject(int id);

    Project addProject(Project project);

    Project updateProject(int id, Project updateProject);

    Project addEmployee(int id, Employee employee);

    Project removeEmployee(int id, Employee employee);

    void removeProject(int id);


}

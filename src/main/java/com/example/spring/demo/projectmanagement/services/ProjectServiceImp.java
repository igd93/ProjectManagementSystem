package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.ProjectMapper;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {


    ProjectRepository repo;
    ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImp(ProjectRepository repo, ProjectMapper projectMapper) {
        this.repo = repo;
        this.projectMapper = projectMapper;
    }


    @Override
    public List<ProjectDTO> allProjects() {
        List<Project> projects =  repo.findAll();
        return projectMapper.entityToDTO(projects);
    }

    @Override
    public ProjectDTO getProjectDTO(int id) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return projectMapper.entityToDTO(project);
        }
        else {
            throw new RuntimeException("Project with this id " + id + " does not exist");
        }
    }

    @Override
    public Project getProject(int id) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        }
        else {
            throw new RuntimeException("Project with this id " + id + " does not exist");
        }

    }

    @Override
    public Project addProject(Project project) {
        return repo.save(project);
    }

    @Override
    public Project updateProject(int id, Project updateProject) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setName(updateProject.getName());
            project.setEmployeeList(updateProject.getEmployeeList());
            return repo.save(project);
        }
        else {
            throw new RuntimeException("Project with id " + id + " cannot be updated, as it does not exist");
        }
    }

    @Override
    public Project addEmployee(int id, Employee employee) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.addEmployee(employee);
            return repo.save(project);
        }
        else {
            throw new RuntimeException("The Project with id " + id + " does not exist");
        }
    }

    @Override
    public Project removeEmployee(int id, Employee employee) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.removeEmployee(employee);
            return repo.save(project);
        }
        else {
            throw new RuntimeException("Project with id " + id + " does not exist");
        }
    }

    @Override
    public void removeProject(int id) {
        repo.deleteById(id);
    }
}

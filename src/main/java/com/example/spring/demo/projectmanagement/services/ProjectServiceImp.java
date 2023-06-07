package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectRepository repo;


    @Override
    public List<Project> allProjects() {
        return repo.findAll();
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
        project.setId(0);
        return repo.save(project);
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

package com.example.spring.demo.projectmanagement.services;

import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    public List<Project> allProjects() {
        return repo.findAll();
    }

    public Project getProject(int id) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        }
        else {
            throw new RuntimeException("Project with this id " + id + "does not exist");
        }
    }

    public Project addProject(Project project) {
        project.setId(0);
        return repo.save(project);
    }

    public Project addEmployee(int id, Employee employee) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.addEmployee(employee);
            return repo.save(project);
        }
        else {
            throw new RuntimeException("The Project with id" + id + "does not exist");
        }
    }

    public Project removeEmployee(int id, Employee employee) {
        Optional<Project> optionalProject = repo.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.removeEmployee(employee);
            return repo.save(project);
        }
        else {
            throw new RuntimeException("Project with id " + id + "does not exist");
        }
    }

    public void removeProject(int id) {
        repo.deleteById(id);
    }
}

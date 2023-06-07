package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> allProjects();

    Project getProject(int id);

    Project addProject(Project project);

    Project addEmployee(int id, Employee employee);

    Project removeEmployee(int id, Employee employee);

    void removeProject(int id);


}

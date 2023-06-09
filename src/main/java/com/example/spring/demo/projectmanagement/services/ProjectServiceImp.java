package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDTO;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDTO;
import com.example.spring.demo.projectmanagement.entities.Employee;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.ProjectMapper;

import com.example.spring.demo.projectmanagement.repositories.EmployeeRepository;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {


    ProjectRepository projectRepository;
    EmployeeRepository employeeRepository;
    ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository,
                             ProjectMapper projectMapper,
                             EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Project> projectIdsToProject(List<Long> projectIds) {
        return projectRepository.findAllById(projectIds);
    }


    @Override
    public List<ProjectResponseDTO> allProjects() {
        List<Project> projects =  projectRepository.findAll();
        return projectMapper.entityToDTO(projects);
    }

    @Override
    public ProjectResponseDTO getProjectDTO(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return projectMapper.entityToDTO(project);
        }
        else {
            throw new RuntimeException("Project with this id " + id + " does not exist");
        }
    }

    @Override
    public ProjectResponseDTO getProject(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return projectMapper.entityToDTO(project);
        }
        else {
            throw new RuntimeException("Project with this id " + id + " does not exist");
        }
    }

    @Override
    public ProjectResponseIdDTO createProject(ProjectRequestDTO project) {
        Project savedProject = projectRepository.save(projectMapper.dTOToEntity(project));
        return projectMapper.idToDTO(savedProject);
    }

    @Override
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO updateProject) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            if (project.getName() != null) project.setName(updateProject.getName());
            Project savedProject = projectRepository.save(project);
            return projectMapper.entityToDTO(savedProject);
        }
        else {
            throw new RuntimeException("Project with id " + id + " cannot be updated, as it does not exist");
        }
    }

//    @Override
//    public void assignEmployee(Long projectId, Long employeeId) {
//        Optional<Project> optionalProject = projectRepository.findById(projectId);
//        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
//        if (optionalProject.isPresent() && optionalEmployee.isPresent()) {
//            Project project = optionalProject.get();
//            Employee employee = optionalEmployee.get();
//            project.addEmployee(employee);
//            projectRepository.save(project);
//        }
//        else {
//            throw new RuntimeException("The Project with id " + projectId
//                    + "or Employee with id " + employeeId  + " does not exist");
//        }
//    }

//    @Override
//    public void unassignEmployee(Long projectId, Long employeeId) {
//        Optional<Project> optionalProject = projectRepository.findById(projectId);
//        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
//        if (optionalProject.isPresent() && optionalEmployee.isPresent()) {
//            Project project = optionalProject.get();
//            Employee employee = optionalEmployee.get();
//            project.removeEmployee(employee);
//            projectRepository.save(project);
//        }
//        else {
//            throw new RuntimeException("The Project with id " + projectId
//                    + "or Employee with id " + employeeId  + " does not exist");
//        }
//    }

    @Override
    public void removeProject(Long id) {
        projectRepository.deleteById(id);
    }
}

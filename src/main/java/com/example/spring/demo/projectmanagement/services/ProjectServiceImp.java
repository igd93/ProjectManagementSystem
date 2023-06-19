package com.example.spring.demo.projectmanagement.services;


import com.example.spring.demo.projectmanagement.dto.ProjectRequestDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseDto;
import com.example.spring.demo.projectmanagement.dto.ProjectResponseIdDto;
import com.example.spring.demo.projectmanagement.entities.Project;
import com.example.spring.demo.projectmanagement.mappers.ProjectMapper;
import com.example.spring.demo.projectmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository,
                             ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;

    }

    @Override
    public List<Project> projectIdsToProject(List<Long> projectIds) {
        return projectRepository.findAllById(projectIds);
    }


    @Override
    public List<ProjectResponseDto> allProjects() {
        Set<Project> projects = new HashSet<>(projectRepository.findAll());
        return projectMapper.entityToDTO(projects);
    }

    @Override
    public ProjectResponseDto getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with this id " + id
                                + " does not exist"));
        return projectMapper.entityToDTO(project);
    }

//    @Override
//    public ProjectResponseDTO getProject(Long id) {
//        Optional<Project> optionalProject = projectRepository.findById(id);
//        if (optionalProject.isPresent()) {
//            Project project = optionalProject.get();
//            return projectMapper.entityToDTO(project);
//        }
//        else {
//            throw new RuntimeException("Project with this id " + id + " does not exist");
//        }
//    }

    @Override
    public ProjectResponseIdDto createProject(ProjectRequestDto project) {
        Project savedProject = projectRepository.save(projectMapper.dTOToEntity(project));
        return projectMapper.idToDTO(savedProject);
    }

    @Override
    public void updateProject(Long id, ProjectRequestDto updateProject) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id "
                        + id + " cannot be updated, as it does not exist"));
        if (project.getName() != null)
            project.setName(updateProject.getName());
        projectRepository.save(project);
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
        projectRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Project with id " + id + " does not exist"));
        projectRepository.deleteById(id);
    }
}

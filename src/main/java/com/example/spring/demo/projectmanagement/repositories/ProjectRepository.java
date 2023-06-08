package com.example.spring.demo.projectmanagement.repositories;

import com.example.spring.demo.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

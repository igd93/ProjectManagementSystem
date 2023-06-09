package com.example.spring.demo.projectmanagement.dto;

public class ProjectResponseDTO {

    private Long id;

    private String name;

    public ProjectResponseDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

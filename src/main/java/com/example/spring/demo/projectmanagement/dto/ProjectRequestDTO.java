package com.example.spring.demo.projectmanagement.dto;

public class ProjectRequestDTO {

    private Long id;

    private String name;

    public ProjectRequestDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

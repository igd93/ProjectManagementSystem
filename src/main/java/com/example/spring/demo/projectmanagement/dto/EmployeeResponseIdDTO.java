package com.example.spring.demo.projectmanagement.dto;

//This should not be reused for both Employee ad Project
public class EmployeeResponseIdDTO {

    private Long id;


    public EmployeeResponseIdDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
package com.example.spring.demo.projectmanagement.dto;

//This should not be reused for both Employee ad Project
public class EmployeeResponseIdDto {

    private Long id;


    public EmployeeResponseIdDto() {

    }

    public EmployeeResponseIdDto(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

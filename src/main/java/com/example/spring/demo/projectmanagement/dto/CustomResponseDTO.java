package com.example.spring.demo.projectmanagement.dto;

public class CustomResponseDTO {

    private long id;
    private String message;

    public CustomResponseDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

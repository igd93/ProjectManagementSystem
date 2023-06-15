package com.example.spring.demo.projectmanagement.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeRequestDto {
        private String name;
        private String familyName;
        private Date dateOfBirth;

        //initialize in constructor
        private Set<Long> projects;

        public EmployeeRequestDto() {
            projects = new HashSet<>();
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setProjects(Set<Long> projects) {
            this.projects = projects;
        }

        public Set<Long> getProjects() {
            return projects;
        }
}

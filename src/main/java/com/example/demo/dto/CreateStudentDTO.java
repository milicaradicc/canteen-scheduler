package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateStudentDTO {

    private String name;
    private String email;
    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public CreateStudentDTO() {
    }

    public CreateStudentDTO(String fullName, String email, boolean isAdmin) {
        this.name = fullName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

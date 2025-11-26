package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedStudentDTO {
    private String id;
    private String name;

    @JsonProperty("isAdmin")
    private boolean isAdmin;

    private String email;

    public CreatedStudentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("isAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    @JsonProperty("isAdmin")
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
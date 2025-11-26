package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetStudentDTO {

    private String id;
    private String name;
    private String email;

    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public GetStudentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @JsonProperty("isAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    @JsonProperty("isAdmin")
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
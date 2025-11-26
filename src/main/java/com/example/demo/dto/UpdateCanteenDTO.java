package com.example.demo.dto;

import com.example.demo.model.WorkingHour;

import java.util.List;

public class UpdateCanteenDTO {

    private String name;
    private String location;
    private int capacity;
    private List<WorkingHourDTO> workingHours;

    public UpdateCanteenDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<WorkingHourDTO> getWorkingHours() { return workingHours; }
    public void setWorkingHours(List<WorkingHourDTO> workingHours) { this.workingHours = workingHours; }
}
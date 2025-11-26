package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Canteen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long numericId;

    @Transient
    private String id;

    private String name;
    private String location;
    private int capacity;

    @OneToMany(mappedBy = "canteen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkingHour> workingHours;

    public Canteen() {}

    public String getId() {
        return numericId != null ? numericId.toString() : null;
    }

    public Long getNumericId() { return numericId; }
    public void setNumericId(Long numericId) { this.numericId = numericId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<WorkingHour> getWorkingHours() { return workingHours; }
    public void setWorkingHours(List<WorkingHour> workingHours) { this.workingHours = workingHours; }
}
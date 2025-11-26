package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long numericId;

    @Transient
    private String id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Canteen canteen;

    private LocalDateTime startTime;

    private int durationMinutes; // 30 or 60

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime createdAt;

    public Reservation() {}

    public Reservation(Student student, Canteen canteen,
                       LocalDateTime startTime, int durationMinutes,
                       ReservationStatus status, LocalDateTime createdAt) {

        this.student = student;
        this.canteen = canteen;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return numericId != null ? numericId.toString() : null;
    }

    public Long getNumericId() { return numericId; }
    public void setNumericId(Long numericId) { this.numericId = numericId; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Canteen getCanteen() { return canteen; }
    public void setCanteen(Canteen canteen) { this.canteen = canteen; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservationDTO {
    private String studentId;
    private String canteenId;
    private LocalDate date;
    private LocalTime time;
    private int duration;

    public CreateReservationDTO() {}

    public CreateReservationDTO(String studentId, String canteenId, LocalDate date, LocalTime time, int duration) {
        this.studentId = studentId;
        this.canteenId = canteenId;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotDTO {
    private LocalDate date;
    private String meal;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    private int remainingCapacity;

    public SlotDTO() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String mealType) {
        this.meal = mealType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(int remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}
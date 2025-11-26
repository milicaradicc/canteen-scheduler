package com.example.demo.dto;

public class WorkingHourDTO {
    private String meal;
    private String from;
    private String to;

    public WorkingHourDTO() {}

    public String getMeal() { return meal; }
    public void setMeal(String meal) { this.meal = meal; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
}

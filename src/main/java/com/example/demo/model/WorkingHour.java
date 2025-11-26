package com.example.demo.model;

import com.example.demo.model.Canteen;
import jakarta.persistence.*;

@Entity
public class WorkingHour {

    @Id
    @GeneratedValue
    private Long id;

    private String meal;

    @Column(name = "from_time")
    private String from;

    @Column(name = "to_time")
    private String to;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    private Canteen canteen;

    public WorkingHour() {}

    // getters i setters
    public String getMeal() { return meal; }
    public void setMeal(String meal) { this.meal = meal; }

    public String getFromTime() { return from; }
    public void setFromTime(String fromTime) { this.from = fromTime; }

    public String getToTime() { return to; }
    public void setToTime(String toTime) { this.to = toTime; }

    public Canteen getCanteen() { return canteen; }
    public void setCanteen(Canteen canteen) { this.canteen = canteen; }
}

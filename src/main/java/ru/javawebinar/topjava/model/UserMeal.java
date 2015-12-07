package ru.javawebinar.topjava.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal implements Serializable{

    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public UserMeal() {
    }
    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getDescription() {
        return description;
    }
    public int getCalories() {
        return calories;
    }
}

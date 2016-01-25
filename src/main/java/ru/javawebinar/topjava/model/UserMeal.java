package ru.javawebinar.topjava.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {

    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    public UserMeal(){
    }

    public UserMeal(UserMeal um){
        this(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories());
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
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

    public void setDateTime(Timestamp timestamp) {
        this.dateTime = timestamp.toLocalDateTime();
    }

    //public void setDateTime(LocalDateTime localDateTime) {
    //    this.dateTime = localDateTime;
    //}

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(UserMeal userMeal);

    void delete(int userId, int id) throws NotFoundException;

    UserMeal get(int userId, int id) throws NotFoundException;

    List<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime);

    void update(UserMeal userMeal);
}

package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealServiceImpl service;

    public UserMeal save(UserMeal userMeal){
     return service.save(userMeal);
    }

    public void delete(int userId, int id) throws NotFoundException {
        service.delete(userId, id);
    }

    public UserMeal get(int userId, int id) throws NotFoundException {
        return service.get(userId, id);
    }

    public List<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime){
        return service.getAll(userId, fromDate, toDate, fromTime, toTime);
    }

    public void update(UserMeal userMeal){
        service.update(userMeal);
    }
}

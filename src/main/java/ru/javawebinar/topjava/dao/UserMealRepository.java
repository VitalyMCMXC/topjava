package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by forza on 08.12.2015.
 */
public class UserMealRepository implements DAO {

    private static int currentMealId = 6;
    private static int caloriesPerDay = 2000;
    private static Map<Integer, UserMeal> mealMap = new HashMap<>();
    static {
        mealMap.put(0, new UserMeal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealMap.put(1, new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealMap.put(2, new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealMap.put(3, new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealMap.put(4, new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealMap.put(5, new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void create(LocalDateTime localDateTime, String description, Integer calory) {
        mealMap.put(currentMealId, new UserMeal(currentMealId++, localDateTime, description, calory));
    }
    @Override
    public void update(Integer id, LocalDateTime localDateTime, String description, Integer calory) {
        mealMap.put(id, new UserMeal(id, localDateTime, description, calory));
    }
    @Override
    public void delete(Integer id) {
        mealMap.remove(id);
    }
    @Override
    public UserMeal getUserMeal(Integer id) {
        return mealMap.get(id);
    }
    @Override
    public List<UserMealWithExceed> display(LocalDateTime startTime, LocalDateTime endTime) {
        Map<LocalDate, Integer> caloriesSumByDate = mealMap.values().stream()
                .collect(Collectors.groupingBy(entry -> entry.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealMap.values().stream()
                .filter(um -> TimeUtil.isBetweenGlobal(um.getDateTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}

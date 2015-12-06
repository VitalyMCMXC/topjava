package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    private static int caloriesPerDay = 2000;
    private static  List<UserMeal> mealList = new ArrayList<>();
    static {
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealList.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public static Map<LocalDateTime, List<UserMealWithExceed>> getFilteredMeals(LocalDateTime startTime, LocalDateTime endTime) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        Map<LocalDateTime, List<UserMealWithExceed>> map =  mealList.stream()
                .filter(um -> TimeUtil.isBetweenGlobal(um.getDateTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.groupingBy(UserMealWithExceed::getDateTime));

        return new TreeMap<>(map);
    }

    public static void addMeal(LocalDateTime dateTime, String description, int calories){
        mealList.add(new UserMeal(dateTime, description, calories));
    }

    public static void deleteMeal(LocalDateTime dateTime, String description){
        for (int i = 0; i < mealList.size(); i++) {
            UserMeal um = mealList.get(i);
            if (um.getDateTime().equals(dateTime) && um.getDescription().equals(description)) {
                mealList.remove(i);
                break;
            }
        }
    }

    public static UserMeal newUserMeal(LocalDateTime dateTime, String description, int calories){
        return new UserMeal(dateTime, description, calories);
    }
}

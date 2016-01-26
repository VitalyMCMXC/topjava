package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;

    public static final UserMeal MEAL_1 = new UserMeal(MEAL_1_ID, LocalDateTime.of(2015, 12, 31, 9, 0), "Завтрак", 500);
    public static final UserMeal MEAL_2 = new UserMeal(MEAL_2_ID, LocalDateTime.of(2015, 12, 31, 14, 0), "Обед", 999);

}

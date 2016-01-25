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

    public static final int MEAL_ID = START_SEQ + 2;

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final UserMeal MEAL = new UserMeal(MEAL_ID, LocalDateTime.of(2015, 12, 31, 9, 0), "Завтрак", 500);


    public static class TestMeal extends UserMeal{

        public TestMeal(UserMeal um){
            this(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories());
        }

        public TestMeal(LocalDateTime dateTime, String description, int calories) {
            this(null, dateTime, description, calories);
        }

        public TestMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
            super(id, dateTime, description, calories);
        }


        public UserMeal asMeal(){
            return new UserMeal(this);
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
}

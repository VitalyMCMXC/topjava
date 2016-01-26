package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by VMoskalik on 25.01.2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServicetest {

    @Autowired
    private UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp(){
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal um = service.get(MEAL_1_ID, USER_ID);
        MATCHER.assertEquals(um, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_2, MEAL_1), service.getAll(USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal um = new UserMeal(LocalDateTime.now(), "testDescription", 50);
        UserMeal created = service.save(um, USER_ID);
        um.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(um, MEAL_2, MEAL_1), service.getAll(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_2), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL_1_ID, 1);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = new UserMeal(MEAL_1);
        updated.setDescription("updatedDescription");
        updated.setCalories(1);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_1_ID, USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        LocalDateTime fromDateTime = LocalDateTime.of(2015, 12, 31, 8, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2015, 12, 31, 13, 0);
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_1), service.getBetweenDateTimes(fromDateTime, toDateTime, USER_ID));
    }
}

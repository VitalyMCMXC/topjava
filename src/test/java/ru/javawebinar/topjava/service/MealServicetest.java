package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.time.LocalDateTime;
import java.util.Arrays;


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
    public void testSave() throws Exception {
        MealTestData.TestMeal tm = new MealTestData.TestMeal(LocalDateTime.now(), "testDescription", 50);
        UserMeal created = service.save(tm.asMeal(), USER_ID);
        tm.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(tm, MEAL), service.getAll(USER_ID));
    }
}

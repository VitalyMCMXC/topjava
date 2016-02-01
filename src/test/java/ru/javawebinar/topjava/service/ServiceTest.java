package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.POSTGRES)
public abstract class ServiceTest {

    private static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceTest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        private void logInfo(Description description, long nanos) {
            LOG.info(String.format("+++ Test %s spent %d microseconds",
                    description.getMethodName(), TimeUnit.NANOSECONDS.toMicros(nanos)));
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, nanos);
        }
    };

    @Autowired
    protected UserMealService mealService;

    @Autowired
    protected UserService userService;

    @Test
    public void testGet() throws Exception {
        User user = userService.get(USER_ID);
        USER_MATCHER.assertEquals(USER, user);

        UserMeal actual = mealService.get(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertEquals(ADMIN_MEAL, actual);
    }


    @Test
    public void testGetNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        userService.get(1);

        thrown.expect(NotFoundException.class);
        mealService.get(MEAL1_ID, ADMIN_ID);

    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = userService.getAll();
        USER_MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);

        MEAL_MATCHER.assertCollectionEquals(USER_MEALS, mealService.getAll(USER_ID));
    }
}

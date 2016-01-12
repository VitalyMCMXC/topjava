package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */

public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    private UserMealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //controller = new InMemoryUserMealRepositoryImpl();
        //System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
        //AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
        //System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = appCtx.getBean(UserMealRestController.class);
        UserMealsUtil.MEAL_LIST.forEach(controller::save);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(LoggedUser.id(),
                id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        controller.save(userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");

            String strFromDate = request.getParameter("fromDate");
            LocalDate fromDate = (strFromDate == null || strFromDate.isEmpty()) ? LocalDate.MIN : LocalDate.parse(strFromDate);
            String strToDate = request.getParameter("toDate");
            LocalDate toDate = (strToDate == null || strToDate.isEmpty()) ? LocalDate.MAX : LocalDate.parse(strToDate);
            String strFromTime = request.getParameter("fromTime");
            LocalTime fromTime = (strFromTime == null || strFromTime.isEmpty()) ? LocalTime.MIN : LocalTime.parse(strFromTime);
            String strToTime = request.getParameter("toTime");
            LocalTime toTime = (strFromTime == null || strToTime.isEmpty()) ? LocalTime.MAX : LocalTime.parse(strToTime);

            request.setAttribute("mealList",
                    UserMealsUtil.getWithExceeded(controller.getAll(LoggedUser.id(), fromDate, toDate, fromTime, toTime), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            controller.delete(LoggedUser.id(), id);
            response.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LoggedUser.id(), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "Перекус", 1000) :
                    controller.get(LoggedUser.id(), getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

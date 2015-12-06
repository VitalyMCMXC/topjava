package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Created by forza on 06.12.2015.
 */
public class MealServlet extends HttpServlet{
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("return mealList");
        LocalDateTime date1 = LocalDateTime.MIN;
        LocalDateTime date2 = LocalDateTime.MAX;

        String action = (req.getParameter("action") == null) ? "default" :  req.getParameter("action");

        switch (action){
            case "delete": //удаление еды
                try {
                    UserMealsUtil.deleteMeal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("descr"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute("mealList", UserMealsUtil.getFilteredMeals(date1, date2));
                req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
                break;
            case "edit": //изменение
                try {
                    UserMealsUtil.deleteMeal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("descr"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute("userMeal", UserMealsUtil.newUserMeal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("descr"), Integer.parseInt(req.getParameter("calory"))));
                req.getRequestDispatcher("meal.jsp").forward(req, resp);
                break;
            default:    //дефолт
                req.setAttribute("mealList", UserMealsUtil.getFilteredMeals(date1, date2));
                req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime date1 = LocalDateTime.MIN;
        LocalDateTime date2 = LocalDateTime.MAX;

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        switch (action){
            case "filter": //фильтрация по заданным датам
                try {
                    date1 = LocalDateTime.parse(req.getParameter("date1"));
                } catch (NullPointerException | DateTimeParseException e){
                    date1 = LocalDateTime.MIN;
                }
                try {
                    date2 = LocalDateTime.parse(req.getParameter("date2"));
                } catch (NullPointerException | DateTimeParseException e){
                    date2 = LocalDateTime.MAX;
                }
                break;
            case "newMeal": //добавление еды
                try {
                    UserMealsUtil.addMeal(LocalDateTime.parse(req.getParameter("date")), req.getParameter("descr"), Integer.parseInt(req.getParameter("calory")));
                } catch (Exception e){
                    e.printStackTrace();
                }
        }

        req.setAttribute("mealList", UserMealsUtil.getFilteredMeals(date1, date2));
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceMock;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealService mealService = new MealServiceMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("operation") != null && req.getParameter("operation").equals("delete")) {
            LOG.debug("deleting meal " + req.getParameter("id"));
            mealService.deleteMeal(Long.parseLong(req.getParameter("id")));
        }
        if (req.getParameter("operation") != null && req.getParameter("operation").equals("update")) {
            LOG.debug("preparing for update meal " + req.getParameter("id"));
            Meal meal = mealService.getById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("meal", meal);
            LOG.debug("forward to mealList from POST");
            req.getRequestDispatcher("/updateMeal.jsp").forward(req, resp);
        }
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealService.getAllMeals(), LocalTime.of(7, 0), LocalTime.of(15, 0), 2000);
        req.setAttribute("mealList", mealWithExceeds);
        LOG.debug("forward to mealList from GET");
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        int calories = Integer.parseInt(req.getParameter("calories"));
        String description = req.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2].substring(0, 2)),
                Integer.parseInt(date.split(":")[0].substring(11, 13)), Integer.parseInt(date.split(":")[1]));
        mealService.addMeal(new Meal(dateTime, description, calories));
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(mealService.getAllMeals(), LocalTime.of(7, 0), LocalTime.of(15, 0), 2000);
        req.setAttribute("mealList", mealWithExceeds);
        LOG.debug("forward to mealList from POST");
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}

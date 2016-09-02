package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)

        );
        List<UserMealWithExceed> exceedList = UserMealsUtil.getFilteredWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(13, 0), 2000);
        exceedList.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerEachDay = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDate = userMeal.getDateTime().toLocalDate();
            caloriesPerEachDay.put(mealDate, caloriesPerEachDay.getOrDefault(mealDate, 0) + userMeal.getCalories());
        }
        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        caloriesPerEachDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return result;
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerEachDay = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return mealList.parallelStream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        caloriesPerEachDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}

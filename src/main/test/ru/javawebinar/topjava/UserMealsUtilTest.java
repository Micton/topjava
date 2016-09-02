package ru.javawebinar.topjava;

import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserMealsUtilTest {

    @Test
    public void testFilteredWithExceeded() {
        List<UserMeal> mealList = new ArrayList<>();
        LocalDate date = LocalDate.of(2012, Month.JANUARY, 1);
        Random r = new Random();
        for (int i = 0; i < 3_000_000; i++) {
            date = date.plus(1, ChronoUnit.DAYS);
            mealList.add(new UserMeal(LocalDateTime.of(date, LocalTime.of(10, 0)), "Breakfast", 300 + r.nextInt(400)));
            mealList.add(new UserMeal(LocalDateTime.of(date, LocalTime.of(13, 0)), "Dinner", 800 + r.nextInt(400)));
            mealList.add(new UserMeal(LocalDateTime.of(date, LocalTime.of(20, 0)), "Supper", 300 + r.nextInt(400)));
        }
//        List<UserMeal> mealList = MealListGenerator.generateMealList(6_000_000, 5);

        Instant beforeRun = Instant.now();
        List<UserMealWithExceed> exceedList = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(13, 0), 2000);
        long runTimeMs = Duration.between(beforeRun, Instant.now()).toMillis();
        long exceedCount = exceedList.stream().filter(UserMealWithExceed::isExceed).count();
        System.out.printf("Implementation 1, mealList.size() = %d, exceedList.size() = %d, exceed count = %d, runtime = %d ms%n", mealList.size(),
                exceedList.size(), exceedCount, runTimeMs);
    }
}

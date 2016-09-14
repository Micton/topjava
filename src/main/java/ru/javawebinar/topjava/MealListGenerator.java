package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.UserMeal;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MealListGenerator {

    private static Random random = new Random();
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static List<UserMeal> generateMealList(int userMeals, int descriptionLength) {
        List<UserMeal> result = new ArrayList<>();
        for (int i = 0; i < userMeals; i++) {
            result.add(new UserMeal(LocalDateTime.of(random.nextInt(2000), random.nextInt(12) + 1, random.nextInt(28) + 1, random.nextInt(24), random.nextInt(60),
                    random.nextInt(60)), generateDescription(descriptionLength), random.nextInt(1000)));
        }
        return result;
    }

    public static String generateDescription(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return result.toString();
    }
}

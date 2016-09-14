package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    long createMeal(Meal meal);

    Meal updateMeal(Meal meal);

    void deleteMeal(long id);

    List<Meal> getAllMeals();
}

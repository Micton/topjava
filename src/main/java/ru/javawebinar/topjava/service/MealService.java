package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(long id);

    List<Meal> getAllMeals();

    Meal getById(long id);
}

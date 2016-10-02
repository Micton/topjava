package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(long id);

    List<Meal> getAllMeals();

    Meal getById(long id);
}

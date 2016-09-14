package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.Optional;

public class MealServiceMock implements MealService {

    List<Meal> meals = MealsUtil.meals;


    @Override
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        Meal taken = getById(meal.getId());
        taken = meal;
    }

    @Override
    public void deleteMeal(long id) {
        meals.remove(getById(id));
    }

    @Override
    public List<Meal> getAllMeals() {
        return meals;
    }

    @Override
    public Meal getById(long id) {
        Optional<Meal> optionalMeal = meals.stream().filter(meal -> meal.getId() == id).findFirst();
        if (optionalMeal.isPresent()) {
            return optionalMeal.get();
        } else return null;
    }
}

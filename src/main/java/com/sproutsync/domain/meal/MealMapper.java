package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.AllergenCreateDto;
import com.sproutsync.domain.meal.dto.response.MealDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class MealMapper {

    public static Set<AllergenCreateDto> mapAllergensToAllergenDto(final MenuDay savedMenuDay) {
        return savedMenuDay.getAllergens().stream()
                .map(a -> {
                    AllergenCreateDto dto = new AllergenCreateDto();
                    dto.setId(a.getId());
                    dto.setAllergen(a.getName());
                    return dto;
                })
                .collect(Collectors.toSet());
    }

    public static List<MealDto> mapFromMealToMealDto(final MenuDay savedMenuDay) {
        return savedMenuDay.getMeals().stream()
                .map(m -> new MealDto(
                        m.getMealType().getName(),
                        m.getDescription()
                ))
                .toList();
    }

    public static Meal mapFromMealDtoToMeal(MealDto dto, MenuDay menuDay, MealType mealType) {
        Meal meal = new Meal();
        meal.setDescription(dto.description());
        meal.setMealType(mealType);
        meal.setMenuDay(menuDay);
        return meal;
    }
}
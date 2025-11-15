package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.response.MealTypeDto;

public class MealMapper {

    private MealMapper() {
    }

    public static Meal toEntity(MealDto mealDto, MenuDay menuDay, MealTypeRepository mealTypeRepository) {
        MealType mealType = mealTypeRepository.findByName(mealDto.getMealType().getName())
                .orElseThrow(() -> new RuntimeException("MealType not found with name: " + mealDto.getMealType().getName()));


        return new Meal(
                null,
                menuDay,
                mealType,
                mealDto.getDescription()
        );
    }

    public static MealDto toDto(Meal meal) {
        MealTypeDto typeDto = new MealTypeDto();
        typeDto.setName(meal.getMealType().getName());

        MealDto dto = new MealDto();
        dto.setDescription(meal.getDescription());

        dto.setMealType(typeDto);

        return dto;
    }
}
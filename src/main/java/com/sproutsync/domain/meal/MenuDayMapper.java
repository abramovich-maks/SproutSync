package com.sproutsync.domain.meal;

import com.sproutsync.domain.allergen.Allergen;
import com.sproutsync.domain.allergen.AllergenMapper;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.meal.dto.response.AllergenDto;
import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.request.MenuDayCreateDto;
import com.sproutsync.domain.meal.dto.response.MenuDayDto;
import com.sproutsync.domain.allergen.AllergenRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuDayMapper {

    private MenuDayMapper() {
    }

    public static MenuDay toEntity(MenuDayCreateDto dto, Group group, MealTypeRepository mealTypeRepository, AllergenRepository allergenRepository) {
        MenuDay menuDay = new MenuDay();
        menuDay.setDate(dto.getDate());
        menuDay.setGroup(group);

        List<Meal> meals = dto.getMeals().stream()
                .map(mealDto -> MealMapper.toEntity(mealDto, menuDay, mealTypeRepository))
                .collect(Collectors.toList());
        menuDay.setMeals(meals);

        Set<Allergen> allergens = dto.getAllergens().stream()
                .map(dtoAllergen -> allergenRepository.findById(dtoAllergen.getId()).orElseThrow(() -> new RuntimeException("Allergen not found with id: " + dtoAllergen.getId())))
                .collect(Collectors.toSet());
        menuDay.setAllergens(allergens);

        return menuDay;
    }

    public static MenuDayDto toDto(MenuDay menuDay) {
        List<MealDto> meals = menuDay.getMeals().stream().map(MealMapper::toDto).toList();

        Set<AllergenDto> allergens = menuDay.getAllergens().stream().map(AllergenMapper::toDto)
                .collect(Collectors.toSet());

        MenuDayDto dto = new MenuDayDto();
        dto.setId(menuDay.getId());
        dto.setDate(menuDay.getDate());
        dto.setMeals(meals);
        dto.setAllergens(allergens);
        dto.setCreatedAt(menuDay.getCreatedAt());
        dto.setUpdatedAt(menuDay.getUpdatedAt());
        return dto;
    }
}
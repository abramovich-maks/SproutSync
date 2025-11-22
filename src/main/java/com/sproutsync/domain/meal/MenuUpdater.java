package com.sproutsync.domain.meal;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.meal.dto.request.AllergenCreateDto;
import com.sproutsync.domain.meal.dto.request.MenuDayUpdateDto;
import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.response.MenuDayUpdateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
class MenuUpdater {

    private final AllergenRetriever allergenRetriever;
    private final MealTypeRetriever mealTypeRetriever;
    private final MenuRetriever menuRetriever;
    private final GroupFacade groupFacade;

    public MenuDayUpdateResponseDto partUpdateMenuDay(Long groupId, LocalDate date, MenuDayUpdateDto menuDayDto) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        MenuDay menu = menuRetriever.findEntityMenuForTheDay(group.groupId(), date);

        if (menuDayDto.date() != null && !menuDayDto.date().equals(menu.getDate())) {
            if (menuRetriever.existsMenuForTheDay(group.groupId(), menuDayDto.date())) {
                throw new IllegalStateException("Menu for date " + menuDayDto.date() + " already exists for group with id " + groupId);
            }
            menu.setDate(menuDayDto.date());
        }
        if (menuDayDto.meals() != null) {
            Set<String> mealTypeNames = menuDayDto.meals().stream()
                    .map(MealDto::mealType)
                    .collect(Collectors.toSet());

            Map<String, MealType> mealTypesMap = mealTypeRetriever.getMealTypesMapByNames(mealTypeNames);

            menu.getMeals().clear();

            List<Meal> newMeals = menuDayDto.meals().stream()
                    .map(dto -> {
                        MealType mealType = mealTypesMap.get(dto.mealType());
                        return MealMapper.mapFromMealDtoToMeal(dto, menu, mealType);
                    })
                    .toList();

            menu.getMeals().addAll(newMeals);
        }
        if (menuDayDto.allergens() != null) {
            Set<Long> allergenIds = menuDayDto.allergens().stream()
                    .map(AllergenCreateDto::getId)
                    .collect(Collectors.toSet());

            Set<Allergen> allergens = allergenRetriever.getAllergensByIds(allergenIds);

            menu.getAllergens().clear();
            menu.getAllergens().addAll(allergens);
        }

        return MenuDayUpdateResponseDto.builder()
                .date(menu.getDate())
                .meals(MealMapper.mapFromMealToMealDto(menu.getMeals()))
                .allergens(MealMapper.mapAllergensToAllergenDto(menu))
                .build();
    }
}
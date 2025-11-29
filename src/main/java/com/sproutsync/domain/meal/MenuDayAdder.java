package com.sproutsync.domain.meal;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.meal.dto.request.AllergenCreateDto;
import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;
import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sproutsync.domain.meal.MealMapper.mapAllergensToAllergenDto;
import static com.sproutsync.domain.meal.MealMapper.mapFromMealToMealDto;

@AllArgsConstructor
class MenuDayAdder {

    private final GroupFacade groupFacade;
    private final AllergenRetriever allergenRetriever;
    private final MealTypeRetriever mealTypeRetriever;
    private final MenuDayRepository menuDayRepository;

    public MenuDayCreateDtoResponse createMenuDay(Long groupId, MenuDayCreateDtoRequest menuDayRequest) {
        GroupResponseDto groupById = groupFacade.getGroupById(groupId);
        if (menuDayRepository.findByGroupIdAndDate(groupById.groupId(), menuDayRequest.date()).isPresent()) {
            throw new IllegalStateException("Menu for date " + menuDayRequest.date() + " already exists for group with id " + groupId);
        }
        Group group = new Group(groupId);
        Set<Long> allergenIds = menuDayRequest.allergens();
        if (allergenIds == null) {
            allergenIds = new HashSet<>();
        }
        Set<Allergen> allergens = allergenRetriever.getAllergensByIds(allergenIds);

        MenuDay menuDayEntity = new MenuDay();
        menuDayEntity.setDate(menuDayRequest.date());
        menuDayEntity.setGroup(group);
        menuDayEntity.setAllergens(allergens);

        List<Meal> meals = createMeals(menuDayRequest.meals(), menuDayEntity);

        menuDayEntity.setMeals(meals);

        MenuDay savedMenuDay = menuDayRepository.save(menuDayEntity);

        List<MealDto> savedMealsDto = mapFromMealToMealDto(savedMenuDay);

        Set<AllergenCreateDto> savedAllergensDto = mapAllergensToAllergenDto(savedMenuDay);

        return MenuDayCreateDtoResponse.builder()
                .date(savedMenuDay.getDate())
                .meals(savedMealsDto)
                .allergens(savedAllergensDto)
                .build();
    }

    private List<Meal> createMeals(List<MealDto> mealDtos, MenuDay menuDayEntity) {
        Set<String> mealTypeNames = mealDtos.stream()
                .map(MealDto::mealType)
                .collect(Collectors.toSet());

        Map<String, MealType> mealTypesMap = mealTypeRetriever.getMealTypesMapByNames(mealTypeNames);

        return mealDtos.stream()
                .map(dto -> {
                    MealType type = mealTypesMap.get(dto.mealType());
                    return MealMapper.mapFromMealDtoToMeal(dto, menuDayEntity, type);
                })
                .collect(Collectors.toList());
    }
}

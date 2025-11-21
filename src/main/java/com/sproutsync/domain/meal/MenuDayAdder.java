package com.sproutsync.domain.meal;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.meal.dto.request.AllergenCreateDto;
import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;
import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import lombok.AllArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class MenuDayAdder {

    private final GroupFacade groupFacade;
    private final MenuDayRepository menuDayRepository;
    private final AllergenRepository allergenRepository;
    private final MealTypeRepository mealTypeRepository;

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
        Set<Allergen> allergens = new HashSet<>(allergenRepository.findAllById(allergenIds));

        if (allergens.size() != allergenIds.size()) {
            throw new EntityNotFoundException("Some allergens were not found");
        }

        MenuDay menuDayEntity = new MenuDay();
        menuDayEntity.setDate(menuDayRequest.date());
        menuDayEntity.setGroup(group);
        menuDayEntity.setAllergens(allergens);

        List<Meal> meals = menuDayRequest.meals().stream()
                .map(dto -> {
                    Meal meal = new Meal();
                    meal.setDescription(dto.description());
                    MealType type = mealTypeRepository.findByName(dto.mealType())
                            .orElseThrow(() -> new RuntimeException("asdf"));
                    meal.setMealType(type);
                    meal.setMenuDay(menuDayEntity);
                    return meal;
                })
                .collect(Collectors.toList());

        menuDayEntity.setMeals(meals);

        MenuDay savedMenuDay = menuDayRepository.save(menuDayEntity);

        List<MealDto> savedMealsDto = savedMenuDay.getMeals().stream()
                .map(m -> new MealDto(
                        m.getMealType().getName(),
                        m.getDescription()
                ))
                .toList();

        Set<AllergenCreateDto> savedAllergensDto = savedMenuDay.getAllergens().stream()
                .map(a -> {
                    AllergenCreateDto dto = new AllergenCreateDto();
                    dto.setId(a.getId());
                    dto.setAllergen(a.getName());
                    return dto;
                })
                .collect(Collectors.toSet());

        return MenuDayCreateDtoResponse.builder()
                .date(savedMenuDay.getDate())
                .meals(savedMealsDto)
                .allergens(savedAllergensDto)
                .build();
    }
}

package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;
import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import com.sproutsync.domain.meal.dto.response.MenuDayResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Transactional
public class MenuDayFacade {

    private final MenuDayAdder menuDayAdder;
    private final MenuRetriever menuRetriever;
    private final MenuDeleter menuDeleter;

    public MenuDayCreateDtoResponse createMenuDay(Long groupId, MenuDayCreateDtoRequest menuDayRequest) {
        return menuDayAdder.createMenuDay(groupId, menuDayRequest);
    }

    //    public MenuDay updateMenuDay(Long groupId, Long menuId, MenuDayUpdateDto menuDayDto) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
//        MenuDay updateMenu = menuDayRepository.findByGroupIdAndId(group.getId(), menuId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
//
//        if (menuDayDto.getDate() != null) {
//            updateMenu.setDate(menuDayDto.getDate());
//        }
//
//        if (menuDayDto.getMeals() != null && !menuDayDto.getMeals().isEmpty()) {
//            Map<String, String> descriptionMap = menuDayDto.getMeals().stream()
//                    .filter(mealDto -> mealDto.getMealType() != null
//                            && mealDto.getMealType().getName() != null
//                            && mealDto.getDescription() != null)
//                    .collect(Collectors.toMap(
//                            mealDto -> mealDto.getMealType().getName().toLowerCase(),
//                            MealDto::getDescription,
//                            (desc1, desc2) -> desc2));
//            updateMenu.getMeals().forEach(existingMeal -> {
//                String typeName = existingMeal.getMealType().getName().toLowerCase();
//                if (descriptionMap.containsKey(typeName)) {
//                    existingMeal.setDescription(descriptionMap.get(typeName));
//                }
//            });
//        }
//
//        if (menuDayDto.getAllergens() != null) {
//            Set<Allergen> allergens = menuDayDto.getAllergens().stream()
//                    .map(dto -> allergenRepository.findById(dto.getId())
//                            .orElseThrow(() -> new EntityNotFoundException("Allergen not found: " + dto.getId())))
//                    .collect(Collectors.toSet());
//            updateMenu.setAllergens(allergens);
//        }
//        return menuDayRepository.save(updateMenu);
//    }
//
    public void deleteMenuDay(Long groupId, LocalDate date) {
        menuDeleter.f(groupId, date);
    }

    public MenuDayResponseDto findMenuByData(Long groupId, LocalDate date) {
        return menuRetriever.findMenuForTheDay(groupId, date);
    }

    public List<MenuDayResponseDto> getAllMenuByGroupId(final Long groupId) {
        return menuRetriever.findAllMenuByGroup(groupId);
    }
}

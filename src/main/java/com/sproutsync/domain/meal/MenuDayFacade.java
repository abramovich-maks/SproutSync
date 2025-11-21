package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;

import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MenuDayFacade {

    private final MenuDayAdder menuDayAdder;

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
//    public void deleteMenuDay(Long groupId, Long menuId) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
//        MenuDay menuDay = menuDayRepository.findByGroupIdAndId(group.getId(), menuId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
//        menuDay.getAllergens().clear();
//        menuDayRepository.save(menuDay);
//        menuDayRepository.delete(menuDay);
//    }
//
//    public MenuDay getMenuDayByGroupId(Long groupId, Long menuId) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
//        return menuDayRepository.findByGroupIdAndId(group.getId(), menuId)
//                .orElseThrow(() -> new EntityNotFoundException("Menu with id " + menuId + " not found"));
//    }
//
//    public List<MenuDay> getAllMenuByGroupId(Long groupId) {
//        return menuDayRepository.getAllByGroupId(groupId);
//    }
//
//    public MenuDay getMenuDayByData(Long groupId, LocalDate date) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
//        return menuDayRepository.findByGroupIdAndDate(groupId,date)
//                .orElseThrow(() -> new EntityNotFoundException("Menu for date " + date + " not found for group with id " + groupId));
//    }
}

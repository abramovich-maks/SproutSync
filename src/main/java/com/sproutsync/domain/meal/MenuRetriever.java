package com.sproutsync.domain.meal;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.meal.dto.response.AllergenDto;
import com.sproutsync.domain.meal.dto.response.MealDto;
import com.sproutsync.domain.meal.dto.response.MenuDayResponseDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.sproutsync.domain.meal.MealMapper.mapFromAllergenToAllergenDto;
import static com.sproutsync.domain.meal.MealMapper.mapFromMealToMealDto;

@AllArgsConstructor
class MenuRetriever {

    private final MenuDayRepository menuDayRepository;
    private final GroupFacade groupFacade;

    public MenuDayResponseDto findMenuForTheDay(Long groupId, LocalDate date) {
        GroupResponseDto groupById = groupFacade.getGroupById(groupId);
        MenuDay menu = menuDayRepository.findByGroupIdAndDate(groupById.groupId(), date)
                .orElseThrow(() -> new MenuDayNotFoundException(date));

        List<MealDto> mealDtos = mapFromMealToMealDto(menu.getMeals());

        Set<AllergenDto> allergenDtos = mapFromAllergenToAllergenDto(menu.getAllergens());

        GroupResponseDto groupInfo = buildGroupResponseDto(groupById);

        return MenuDayResponseDto.builder()
                .id(menu.getId())
                .date(menu.getDate())
                .group(groupInfo)
                .meals(mealDtos)
                .allergens(allergenDtos)
                .updatedAt(menu.getUpdatedAt())
                .createdAt(menu.getCreatedAt())
                .build();
    }

    private static GroupResponseDto buildGroupResponseDto(final GroupResponseDto groupById) {
        GroupResponseDto groupInfo = GroupResponseDto.builder()
                .groupId(groupById.groupId())
                .groupName(groupById.groupName())
                .description(groupById.description())
                .build();
        return groupInfo;
    }
}

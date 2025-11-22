package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.AllergenCreateDto;
import com.sproutsync.domain.meal.dto.response.MealDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record MenuDayUpdateResponseDto(
        LocalDate date,

        List<MealDto> meals,

        Set<AllergenCreateDto> allergens
) {
}

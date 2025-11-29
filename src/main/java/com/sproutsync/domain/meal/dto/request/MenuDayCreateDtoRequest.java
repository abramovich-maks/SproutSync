package com.sproutsync.domain.meal.dto.request;

import com.sproutsync.domain.meal.dto.response.MealDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Schema(name = "MenuDayCreateRequest", description = "Request payload for creating a new menu for a group")
public record MenuDayCreateDtoRequest(
        @Schema(description = "Date of the menu", example = "2025-09-01", type = "string", format = "date")
        LocalDate date,

        @Schema(description = "List of meals for the day")
        List<MealDto> meals,

        @Schema(description = "Set of IDs allergens for the menu")
        Set<Long> allergens
) {
}

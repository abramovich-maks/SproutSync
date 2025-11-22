package com.sproutsync.domain.meal.dto.request;


import com.sproutsync.domain.meal.dto.response.MealDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Schema(name = "MenuDayUpdateRequest", description = "Request payload for updating an existing menu")
public record MenuDayUpdateDto(
        @Schema(description = "Updated date of the menu", example = "2025-09-02", type = "string", format = "date")
        LocalDate date,

        @Schema(description = "Updated list of meals")
        List<MealDto> meals,

        @Schema(description = "Updated set of allergens")
        Set<AllergenCreateDto> allergens
) {
}

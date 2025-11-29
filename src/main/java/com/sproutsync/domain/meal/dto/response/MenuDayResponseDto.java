package com.sproutsync.domain.meal.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Schema(name = "MenuDayResponse", description = "Response DTO representing a daily menu")
public record MenuDayResponseDto(

        @Schema(description = "Menu ID")
        Long id,

        @Schema(description = "Date of the menu")
        LocalDate date,

        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        @Schema(description = "List of meals for the day")
        List<MealDto> meals,

        @Schema(description = "Set of allergens for the menu")
        Set<AllergenDto> allergens,

        @Schema(description = "Timestamp when the menu was created")
        LocalDateTime createdAt,

        @Schema(description = "Timestamp when the menu was last updated")
        LocalDateTime updatedAt
) {
}

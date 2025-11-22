package com.sproutsync.domain.meal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "MealResponse", description = "Response DTO representing a meal")
@Builder
public record MealDto
        (@Schema(description = "Meal type", example = "Zupa")
         String mealType,

         @Schema(description = "Description of the meal", example = "Rosół z makaronem.")
         String description) {
}

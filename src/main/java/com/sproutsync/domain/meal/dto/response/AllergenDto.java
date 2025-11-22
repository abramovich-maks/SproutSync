package com.sproutsync.domain.meal.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "AllergenResponse", description = "Response DTO representing an allergen")
public record AllergenDto(
        @Schema(description = "Allergen ID", example = "4")
        Long id,

        @Schema(description = "Name of the allergen", example = "Mleko")
        String name
) {
}

package com.sproutsync.domain.group.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GroupUpdateRequestDto", description = "Request payload for creating or updating a group")
public record GroupUpdateRequestDto(
        @Schema(description = "Name of the group", example = "Biedronki")
        String name,

        @Schema(description = "Short description of the group", example = "This is the preschool group for 3-4 year olds")
        String description
) {
}

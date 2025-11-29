package com.sproutsync.infrastructure.user.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserControllerResponseDto(

        @Schema(description = "User ID")
        Long id,

        @Schema(description = "User's first name / username")

        String username,

        @Schema(description = "User's surname")
        String surname,

        @Schema(description = "User's email address")
        String email,

        @Schema(description = "Set of role names assigned to the user")
        Set<String> roles
) {
}

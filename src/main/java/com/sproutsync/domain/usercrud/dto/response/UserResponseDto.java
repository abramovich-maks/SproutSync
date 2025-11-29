package com.sproutsync.domain.usercrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

@Builder
@Schema(name = "UserResponse", description = "Response DTO representing a user")
public record UserResponseDto(
        @Schema(description = "User ID")
        Long id,

        @Schema(description = "User's first name / username")

        String username,

        @Schema(description = "User's surname")
        String surname,

        @Schema(description = "User's email address")
        String email,

        @Schema(description = "Set of role names assigned to the user")
        Set<String> roles) {
}

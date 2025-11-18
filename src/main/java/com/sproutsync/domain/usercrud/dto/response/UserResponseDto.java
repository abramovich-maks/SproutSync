package com.sproutsync.domain.usercrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

@Builder
@Schema(name = "UserResponse", description = "Response DTO representing a user")
public record UserResponseDto(
        @Schema(description = "User ID", example = "101")
        Long id,

        @Schema(description = "User's first name / username", example = "Jan")

        String username,

        @Schema(description = "User's surname", example = "Kowalski")
        String surname,

        @Schema(description = "User's email address", example = "jan.kowalski@mail.com", format = "email")
        String email,

        @Schema(description = "Set of role names assigned to the user", example = "ROLE_USER, ROLE_ADMIN")
        Set<String> roles) {

}

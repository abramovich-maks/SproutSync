package com.sproutsync.domain.usercrud.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(name = "UserUpdateRequest", description = "Request payload for updating an existing user")
public record UserUpdateRequestDto(
        @Schema(description = "Updated first name / username", example = "Marek")
        String username,

        @Schema(description = "Updated surname", example = "Nowak")
        String surname,

        @Schema(description = "Updated email address", example = "marek.nowak@mail.com", format = "email")
        String email,

        @Schema(description = "Updated password", example = "New_Update_Pass!")
        String password,

        @Schema(description = "IDs of roles assigned to the user", example = "[1, 2,3]")
        Set<Long> roleIds) {

}


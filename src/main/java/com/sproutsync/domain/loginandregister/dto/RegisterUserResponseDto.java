package com.sproutsync.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegisterUserResponseDto(
        String username,
        String surname,
        String email,
        String message
) {
}

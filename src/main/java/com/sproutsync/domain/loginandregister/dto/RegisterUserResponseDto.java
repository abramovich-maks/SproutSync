package com.sproutsync.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegisterUserResponseDto(
        String email,
        String message
) {
}

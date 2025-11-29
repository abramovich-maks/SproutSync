package com.sproutsync.domain.loginandregister.dto;

import lombok.Builder;


@Builder
public record UserDto(
        Long userId,
        String username,
        String surname,
        String mail
) {
}

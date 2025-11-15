package com.sproutsync.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserDto(
        String userId,
        String mail,
        String password
) {
}

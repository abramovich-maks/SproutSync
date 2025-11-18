package com.sproutsync.domain.usercrud.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserUpdateResponseDto(
        Long id,
        String username,
        String surname,
        String email,
        Set<Long> roleIds
) {}



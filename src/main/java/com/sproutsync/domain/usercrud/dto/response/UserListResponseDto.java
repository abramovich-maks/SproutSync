package com.sproutsync.domain.usercrud.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UserListResponseDto(
        List<UserResponseDto> users,
        String message
) {
}

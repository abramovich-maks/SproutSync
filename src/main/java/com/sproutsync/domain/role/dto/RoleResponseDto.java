package com.sproutsync.domain.role.dto;

import lombok.Builder;

@Builder
public record RoleResponseDto(
        Long roleId,
        String role
) {
}

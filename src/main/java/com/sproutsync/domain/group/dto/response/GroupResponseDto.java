package com.sproutsync.domain.group.dto.response;

import lombok.Builder;

@Builder
public record GroupResponseDto(
        Long groupId,
        String groupName,
        String description) {
}
package com.sproutsync.domain.group.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteGroupResponseDto(
        String message,
        HttpStatus status
) {
}

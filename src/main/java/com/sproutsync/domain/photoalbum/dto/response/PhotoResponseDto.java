package com.sproutsync.domain.photoalbum.dto.response;

import lombok.Builder;

@Builder
public record PhotoResponseDto(
        Long id,
        String uri
) {
}

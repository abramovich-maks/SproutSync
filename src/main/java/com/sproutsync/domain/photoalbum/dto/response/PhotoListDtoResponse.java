package com.sproutsync.domain.photoalbum.dto.response;

import lombok.Builder;

@Builder
public record PhotoListDtoResponse(
        Long id,
        String uri
) {
}

package com.sproutsync.domain.photoalbum.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PhotoAlbumResponseDto(
        @Schema(description = "Album ID")
        Long id,

        @Schema(description = "Description of the album")
        String description) {
}
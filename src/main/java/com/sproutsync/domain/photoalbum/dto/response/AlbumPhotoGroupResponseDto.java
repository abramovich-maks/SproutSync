package com.sproutsync.domain.photoalbum.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AlbumPhotoGroupResponseDto(
        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        PhotoAlbumResponseDto album,

        PhotoResponseDto photo
) {
}

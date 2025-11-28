package com.sproutsync.domain.photoalbum.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record PhotoAlbumGroupResponseDto(
        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        @Schema(description = "Photo of the stored album")
        List<PhotoAlbumResponseDto> album
) {
}

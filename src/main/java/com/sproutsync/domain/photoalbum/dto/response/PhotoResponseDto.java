package com.sproutsync.domain.photoalbum.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record PhotoResponseDto(
        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        PhotoAlbumResponseDto album,

        List<PhotoListDtoResponse> photo
) {
}

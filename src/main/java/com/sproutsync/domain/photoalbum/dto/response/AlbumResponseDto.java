package com.sproutsync.domain.photoalbum.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(name = "AlbumResponse", description = "Response DTO representing an album in the gallery")
public record AlbumResponseDto(
        @Schema(description = "Album ID")
        Long id,

        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        @Schema(description = "Photo of the stored album")
        List<String> photo,

        @Schema(description = "Description of the album")
        String description,

        @Schema(description = "Timestamp when the photo was uploaded")
        LocalDateTime createdAt,

        @Schema(description = "Timestamp when the photo metadata was last updated")
        LocalDateTime updatedAt,

        @Schema(description = "User who created the announcement")
        UserDto createdBy) {
}

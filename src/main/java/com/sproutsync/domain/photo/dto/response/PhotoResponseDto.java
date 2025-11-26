package com.sproutsync.domain.photo.dto.response;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(name = "PhotoResponse", description = "Response DTO representing a photo in the gallery")
public record PhotoResponseDto(
        @Schema(description = "Photo ID")
        Long id,

        @Schema(description = "The group to which the menu belongs")
        GroupResponseDto group,

        @Schema(description = "URL of the stored photo")
        List<String> url,

        @Schema(description = "Description of the photo")
        String description,

        @Schema(description = "Timestamp when the photo was uploaded")
        LocalDateTime createdAt,

        @Schema(description = "Timestamp when the photo metadata was last updated")
        LocalDateTime updatedAt,

        @Schema(description = "User who created the announcement")
        UserDto createdBy) {
}

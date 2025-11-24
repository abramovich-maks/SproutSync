package com.sproutsync.domain.announcement.dto.response;


import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(name = "AnnouncementResponse", description = "Response DTO representing an announcement")
public record AnnouncementRetrieveResponseDto(
        @Schema(description = "Announcement ID")
        Long id,

        @Schema(description = "Group the announcement belongs to")
        GroupResponseDto group,

        @Schema(description = "Title of the announcement")
        String title,

        @Schema(description = "Message content of the announcement")
        String message,

        @Schema(description = "URL to a photo attached to the announcement")
        String photo,

        @Schema(description = "Timestamp when the announcement was created")
        LocalDateTime createdAt,

        @Schema(description = "Timestamp when the announcement was last updated")
        LocalDateTime updatedAt,

        @Schema(description = "User who created the announcement")
        UserDto createdBy) {
}
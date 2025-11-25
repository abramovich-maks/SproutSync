package com.sproutsync.domain.announcement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AnnouncementUpdateResponseDto(

        @Schema(description = "Updated title of the announcement")
        String title,

        @Schema(description = "Updated message content")
        String message,

        @Schema(description = "Updated photo URL")
        String photo
) {
}

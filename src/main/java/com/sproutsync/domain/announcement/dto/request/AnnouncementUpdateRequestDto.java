package com.sproutsync.domain.announcement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AnnouncementUpdateRequest", description = "Request payload for updating an existing announcement")
public record AnnouncementUpdateRequestDto(
        @Schema(description = "Updated title of the announcement", example = "Parent Meeting (Rescheduled)")
        String title,

        @Schema(description = "Updated message content", example = "The meeting has been moved to Wednesday due to public holiday.")
        String message,

        @Schema(description = "Updated photo URL", example = "https://example.com/announcements/meeting_updated.jpg")
        String photo
) {
}
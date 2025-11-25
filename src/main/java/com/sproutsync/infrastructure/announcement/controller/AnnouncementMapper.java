package com.sproutsync.infrastructure.announcement.controller;

import org.springframework.http.HttpStatus;

class AnnouncementMapper {
    public static DeleteAnnouncementResponseDto getDeleteAnnouncementResponseDto(Long announcementId) {
        return new DeleteAnnouncementResponseDto("Announcement with id [%d] deleted successfully.".formatted(announcementId), HttpStatus.OK);
    }
}

package com.sproutsync.domain.announcement;

public class AnnouncementNotFoundException extends RuntimeException {

    private final Long announcementId;

    public AnnouncementNotFoundException(final Long announcementId) {
        super(String.format("Announcement with id [%d] not found", announcementId));
        this.announcementId = announcementId;
    }
}
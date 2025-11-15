package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementUpdateRequestDto;

import java.util.List;

public interface AnnouncementService {

    Announcement createAnnouncement(Long groupId, Announcement announcement);

    Announcement updateAnnouncement(Long groupId, Long announcementId, AnnouncementUpdateRequestDto announcementDto);

    void deleteAnnouncement(Long groupId, Long activityId);

    Announcement getAnnouncementByGroup(Long groupId, Long announcementId);

    List<Announcement> getAllAnnouncementsByGroupId(Long groupId);

}

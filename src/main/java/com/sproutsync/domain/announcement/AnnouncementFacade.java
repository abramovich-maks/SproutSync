package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.announcement.dto.request.AnnouncementUpdateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementCreateResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementUpdateResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AnnouncementFacade {

    private final AnnouncementAdder announcementAdder;
    private final AnnouncementRetriever announcementRetriever;
    private final AnnouncementDeleter announcementDeleter;
    private final AnnouncementUpdater announcementUpdater;

    public AnnouncementCreateResponseDto createAnnouncement(Long groupId, AnnouncementCreateRequestDto requestDto) {
        return announcementAdder.createAnnouncement(groupId, requestDto);
    }

    public AnnouncementUpdateResponseDto updateAnnouncement(Long groupId, Long announcementId, AnnouncementUpdateRequestDto updateDto) {
        return announcementUpdater.updateAnnouncement(groupId, announcementId, updateDto);
    }

    public void deleteAnnouncement(Long groupId, Long announcementId) {
        announcementDeleter.deleteById(groupId, announcementId);
    }

    public AnnouncementRetrieveResponseDto getAnnouncementByGroup(Long groupId, Long announcementId) {
        return announcementRetriever.getAnnouncementByGroup(groupId, announcementId);
    }

    public List<AnnouncementRetrieveResponseDto> getAllAnnouncementsByGroupId(Long groupId) {
        return announcementRetriever.getAnnouncementsByGroup(groupId);
    }
}

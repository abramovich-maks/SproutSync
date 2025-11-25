package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.sproutsync.domain.announcement.AnnouncementMapper.getGroupDto;
import static com.sproutsync.domain.announcement.AnnouncementMapper.mapFromAnnouncementToAnnouncementRetrieveDto;

@AllArgsConstructor
class AnnouncementRetriever {

    private final AnnouncementRepository announcementRepository;
    private final GroupFacade groupFacade;

    AnnouncementRetrieveResponseDto getAnnouncementByGroup(final Long groupId, final Long announcementId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        Announcement announcement = announcementRepository.findByGroupIdAndId(group.groupId(), announcementId)
                .orElseThrow(() -> new AnnouncementNotFoundException(announcementId));
        return mapFromAnnouncementToAnnouncementRetrieveDto(announcement, getGroupDto(group));
    }

    List<AnnouncementRetrieveResponseDto> getAnnouncementsByGroup(final Long groupId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        GroupResponseDto groupDto = getGroupDto(group);

        List<Announcement> allByGroupId = announcementRepository.findAllByGroupId(group.groupId());

        return allByGroupId.stream()
                .map(announcement -> mapFromAnnouncementToAnnouncementRetrieveDto(announcement, groupDto))
                .toList();
    }
}

package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class AnnouncementRetriever {

    private final AnnouncementRepository announcementRepository;
    private final GroupFacade groupFacade;

    AnnouncementRetrieveResponseDto getAnnouncementByGroup(final Long groupId, final Long announcementId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        Announcement announcement = announcementRepository.findByGroupIdAndId(group.groupId(), announcementId)
                .orElseThrow(() -> new IllegalArgumentException("Announcement " + announcementId + " not found in group " + groupId));

        GroupResponseDto groupDto = GroupResponseDto.builder()
                .groupId(group.groupId())
                .groupName(group.groupName())
                .description(group.description())
                .build();

        User creator = announcement.getCreatedBy();
        UserDto creatorDto = UserDto.builder()
                .userId(creator.getId())
                .username(creator.getUsername())
                .surname(creator.getSurname())
                .mail(creator.getEmail())
                .build();

        return AnnouncementRetrieveResponseDto.builder()
                .id(announcement.getId())
                .group(groupDto)
                .title(announcement.getTitle())
                .message(announcement.getMessage())
                .photo(announcement.getPhoto())
                .createdAt(announcement.getCreatedAt())
                .updatedAt(announcement.getUpdatedAt())
                .createdBy(creatorDto)
                .build();
    }
}

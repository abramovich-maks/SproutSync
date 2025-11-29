package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.response.AnnouncementCreateResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.dto.UserDto;

class AnnouncementMapper {

    public static UserDto buildUserDto(final User creator) {
        return UserDto.builder()
                .userId(creator.getId())
                .username(creator.getUsername())
                .surname(creator.getSurname())
                .mail(creator.getEmail())
                .build();
    }

    public static GroupResponseDto getGroupDto(final GroupResponseDto group) {
        return GroupResponseDto.builder()
                .groupId(group.groupId())
                .groupName(group.groupName())
                .description(group.description())
                .build();
    }
    public static AnnouncementRetrieveResponseDto mapFromAnnouncementToAnnouncementRetrieveDto(Announcement announcement, GroupResponseDto groupDto) {
        UserDto creatorDto = buildUserDto(announcement.getCreatedBy());
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

    public static AnnouncementCreateResponseDto fromAnnouncementToAnnouncementCreateDto(final Announcement announcement, final GroupResponseDto groupInfo) {
        UserDto creatorDto = buildUserDto(announcement.getCreatedBy());
        return AnnouncementCreateResponseDto.builder()
                .id(announcement.getId())
                .group(groupInfo)
                .title(announcement.getTitle())
                .message(announcement.getMessage())
                .photo(announcement.getPhoto())
                .createdAt(announcement.getCreatedAt())
                .createdBy(creatorDto)
                .build();
    }
}

package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementResponseDto;
import com.sproutsync.domain.announcement.dto.request.AnnouncementUpdateRequestDto;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.loginandregister.User;

public class AnnouncementMapper {

    private AnnouncementMapper() {
    }

    public static Announcement toEntity(AnnouncementResponseDto dto, Group group, User user) {
        return new Announcement(
                null,
                group,
                dto.getTitle(),
                dto.getMessage(),
                dto.getPhoto(),
                null,
                null,
                user
        );
    }

    public static Announcement toEntity(AnnouncementUpdateRequestDto dto, Group group, User user) {
        return new Announcement(
                null,
                group,
                dto.getTitle(),
                dto.getMessage(),
                dto.getPhoto(),
                null,
                null,
                user
        );
    }

    public static Announcement toEntity(AnnouncementCreateRequestDto dto, Group group, User user) {
        return new Announcement(
                null,
                group,
                dto.getTitle(),
                dto.getMessage(),
                dto.getPhoto(),
                null,
                null,
                user
        );
    }

    public static AnnouncementResponseDto toDto(Announcement entity) {
        AnnouncementResponseDto dto = new AnnouncementResponseDto();
        dto.setId(entity.getId());
        dto.setGroupId(entity.getGroup().getId());
        dto.setTitle(entity.getTitle());
        dto.setMessage(entity.getMessage());
        dto.setPhoto(entity.getPhoto());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setCreatedByUserId(entity.getCreatedBy().getId());
        return dto;
    }
}

package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;


@AllArgsConstructor
class AnnouncementAdder {

    private final AnnouncementRepository announcementRepository;
    private final GroupFacade groupFacade;
    private final LoginAndRegisterFacade loginAndRegisterFacade;

    public AnnouncementCreateResponseDto createAnnouncement(Long groupId, AnnouncementCreateRequestDto requestDto) {
        User user = loginAndRegisterFacade.getUserPrincipal();

        UserDto currentUserDto = UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .surname(user.getSurname())
                .mail(user.getEmail())
                .build();

        GroupResponseDto groupById = groupFacade.getGroupById(groupId);
        Group group = new Group(groupById.groupId());
        Announcement announcement = new Announcement();
        announcement.setTitle(requestDto.getTitle());
        announcement.setMessage(requestDto.getMessage());
        announcement.setPhoto(requestDto.getPhoto());
        announcement.setGroup(group);
        announcement.setCreatedBy(user);
        Announcement savedAnnouncement = announcementRepository.save(announcement);

        GroupResponseDto groupInfo = GroupResponseDto.builder()
                .groupId(groupById.groupId())
                .groupName(groupById.groupName())
                .description(groupById.description())
                .build();

        return AnnouncementCreateResponseDto.builder()
                .id(savedAnnouncement.getId())
                .group(groupInfo)
                .title(savedAnnouncement.getTitle())
                .message(savedAnnouncement.getMessage())
                .photo(savedAnnouncement.getPhoto())
                .createdAt(savedAnnouncement.getCreatedAt())
                .createdBy(currentUserDto)
                .build();

    }
}
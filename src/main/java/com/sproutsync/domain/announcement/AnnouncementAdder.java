package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementCreateResponseDto;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.User;
import lombok.AllArgsConstructor;

import static com.sproutsync.domain.announcement.AnnouncementMapper.fromAnnouncementToAnnouncementCreateDto;
import static com.sproutsync.domain.announcement.AnnouncementMapper.getGroupDto;


@AllArgsConstructor
class AnnouncementAdder {

    private final AnnouncementRepository announcementRepository;
    private final GroupFacade groupFacade;
    private final LoginAndRegisterFacade loginAndRegisterFacade;

    public AnnouncementCreateResponseDto createAnnouncement(Long groupId, AnnouncementCreateRequestDto requestDto) {
        User user = loginAndRegisterFacade.getUserPrincipal();

        GroupResponseDto groupById = groupFacade.getGroupById(groupId);
        Group group = new Group(groupById.groupId());
        Announcement announcement = new Announcement();
        announcement.setTitle(requestDto.getTitle());
        announcement.setMessage(requestDto.getMessage());
        announcement.setPhoto(requestDto.getPhoto());
        announcement.setGroup(group);
        announcement.setCreatedBy(user);
        Announcement savedAnnouncement = announcementRepository.save(announcement);

        GroupResponseDto groupInfo = getGroupDto(groupById);

        return fromAnnouncementToAnnouncementCreateDto(savedAnnouncement, groupInfo);
    }
}
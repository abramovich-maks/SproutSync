package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementUpdateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementUpdateResponseDto;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
class AnnouncementUpdater {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementRetriever announcementRetriever;
    private final GroupFacade groupFacade;


    AnnouncementUpdateResponseDto updateAnnouncement(final Long groupId, final Long announcementId, final AnnouncementUpdateRequestDto updateDto) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        Announcement announcementEntity = announcementRetriever.getAnnouncementEntityByGroup(group.groupId(), announcementId);

        AnnouncementUpdateResponseDto.AnnouncementUpdateResponseDtoBuilder dtoBuilder = AnnouncementUpdateResponseDto.builder();
        if (updateDto.title() != null) {
            announcementEntity.setTitle(updateDto.title());
            dtoBuilder.title(updateDto.title());
        }
        if (updateDto.message() != null) {
            announcementEntity.setMessage(updateDto.message());
            dtoBuilder.message(updateDto.message());
        }
        if (updateDto.photo() != null) {
            announcementEntity.setPhoto(updateDto.photo());
            dtoBuilder.photo(updateDto.photo());
        }

        announcementRepository.save(announcementEntity);

        return dtoBuilder.build();
    }
}

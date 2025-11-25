package com.sproutsync.domain.announcement;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Log4j2
class AnnouncementDeleter {

    private final AnnouncementRepository announcementRepository;
    private final GroupFacade groupFacade;

    void deleteById(final Long groupId, final Long announcementId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);
        Announcement announcement = announcementRepository.findByGroupIdAndId(group.groupId(), announcementId)
                .orElseThrow(() -> new AnnouncementNotFoundException(announcementId));
        announcementRepository.delete(announcement);
        log.info("Announcement {} from group {} deleted", announcementId, group.groupId());
    }
}

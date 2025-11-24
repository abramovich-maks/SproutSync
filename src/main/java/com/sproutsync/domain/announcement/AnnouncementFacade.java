package com.sproutsync.domain.announcement;

import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementCreateResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnnouncementFacade {

    private final AnnouncementAdder announcementAdder;
    private final AnnouncementRetriever announcementRetriever;

    public AnnouncementCreateResponseDto createAnnouncement(Long groupId, AnnouncementCreateRequestDto requestDto) {
        return announcementAdder.createAnnouncement(groupId, requestDto);
    }
//    @Override
//    public Announcement updateAnnouncement(Long groupId, Long announcementId, AnnouncementUpdateRequestDto updateDto) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id:" + groupId + " not found"));
//        Announcement updateAnnouncement = announcementRepository.findByGroupIdAndId(group.getId(), announcementId)
//                .orElseThrow(() -> new EntityNotFoundException("Announcement with id:" + announcementId + " not found"));
//        if (updateDto.getTitle() != null) {
//            updateAnnouncement.setTitle(updateDto.getTitle());
//        }
//        if (updateDto.getMessage() != null) {
//            updateAnnouncement.setMessage(updateDto.getMessage());
//        }
//        if (updateDto.getPhoto() != null) {
//            updateAnnouncement.setPhoto(updateDto.getPhoto());
//        }
//        return announcementRepository.save(updateAnnouncement);
//    }
//
//    @Override
//    public void deleteAnnouncement(Long groupId,Long announcementId) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id:" + groupId + " not found"));
//        Announcement existing = announcementRepository.findByGroupIdAndId(group.getId(), announcementId)
//                .orElseThrow(() -> new EntityNotFoundException("Announcement with id:" + announcementId + " not found"));
//        announcementRepository.deleteById(existing.getId());
//    }
    public AnnouncementRetrieveResponseDto getAnnouncementByGroup(Long groupId, Long announcementId) {
        return announcementRetriever.getAnnouncementByGroup(groupId, announcementId);
    }
//    @Override
//    public List<Announcement> getAllAnnouncementsByGroupId(Long groupId) {
//        return announcementRepository.findAllByGroupId(groupId);
//    }
}

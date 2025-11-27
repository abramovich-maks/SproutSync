package com.sproutsync.domain.photo;

import com.sproutsync.domain.photo.dto.request.PhotoUploadRequestDto;
import com.sproutsync.domain.photo.dto.response.PhotoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class PhotoFacade {

    private final PhotoUploader photoUploader;
    private final PhotoDeleter photoDeleter;


    @Transactional
    public PhotoResponseDto uploadPhoto(Long groupId, PhotoUploadRequestDto uploadDto) {
        return photoUploader.uploadPhoto(groupId, uploadDto);
    }

    @Transactional
    public void deletePhoto(Long groupId, Long photoId) {
        photoDeleter.deletePhoto(groupId, photoId);
    }


    @Transactional
    public void deleteUrl(Long groupId, Long photoId, Long urlId) {
        photoDeleter.deleteUrl(groupId, photoId, urlId);
    }
//
//    public List<Photo> getAllPhotosByGroupId(Long idGroup) {
//        Group group = groupRepository.findById(idGroup)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + idGroup + " not found"));
//        return photoRepository.findByGroupId(group.getId());
//    }
}
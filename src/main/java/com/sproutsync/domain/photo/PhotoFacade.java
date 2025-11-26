package com.sproutsync.domain.photo;

import com.sproutsync.domain.photo.dto.request.PhotoUploadRequestDto;
import com.sproutsync.domain.photo.dto.response.PhotoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class PhotoFacade {

    private final PhotoUploader photoUploader;


    @Transactional
    public PhotoResponseDto uploadPhoto(Long groupId, PhotoUploadRequestDto uploadDto) {
        return photoUploader.uploadPhoto(groupId, uploadDto);
    }
//
//
//    @Transactional
//    public void deletePhoto(Long groupId, Long photoId) {
//        Group group = groupRepository.findById(groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
//        Photo photo = photoRepository.findByIdAndGroupId(photoId, groupId)
//                .orElseThrow(() -> new EntityNotFoundException("Photo with id:" + photoId + " not found in group:" + group));
//
//        s3Service.deleteFile(photo.getUrl());
//        photoRepository.deleteByIdAndGroupId(photoId, groupId);
//    }
//
//    public List<Photo> getAllPhotosByGroupId(Long idGroup) {
//        Group group = groupRepository.findById(idGroup)
//                .orElseThrow(() -> new EntityNotFoundException("Group with id " + idGroup + " not found"));
//        return photoRepository.findByGroupId(group.getId());
//    }
}
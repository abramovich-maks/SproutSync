package com.sproutsync.domain.photo;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.infrastructure.s3aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
class PhotoDeleter {

    private final PhotoRepository photoRepository;
    private final UrlRepository urlRepository;
    private final GroupFacade groupFacade;
    private final S3Service s3Service;

    public void deletePhoto(final Long groupId, final Long photoId) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);

        Photo photo = photoRepository.findByIdAndGroupId(photoId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Photo with id:" + photoId + " not found in group:" + groupDto.groupId()));

        photo.getUrl().forEach(photoUrl -> {
            if (photoUrl.getUrl() != null && !photoUrl.getUrl().isBlank()) {
                s3Service.deleteFile(photoUrl.getUrl());
            }
        });

        photoRepository.delete(photo);
    }

    public void deleteUrl(final Long groupId, final Long photoId, final Long urlId) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);

        Photo photo = photoRepository.findByIdAndGroupId(photoId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Photo with id:" + photoId + " not found in group:" + groupDto.groupId()));

        PhotoUrl photoUrl = urlRepository.findById(urlId)
                .orElseThrow(() -> new EntityNotFoundException("PhotoUrl with id:" + urlId + " not found"));

        if (photoUrl.getPhoto() == null || !photoUrl.getPhoto().getId().equals(photo.getId())) {
            throw new EntityNotFoundException("PhotoUrl does not belong to photo " + photoId);
        }

        String urlToBeDeleted = photoUrl.getUrl();
        if (urlToBeDeleted != null && !urlToBeDeleted.isBlank()) {
            s3Service.deleteFile(urlToBeDeleted);
        }

        photo.removeUrl(photoUrl);
        urlRepository.delete(photoUrl);
        photoRepository.save(photo);
    }
}

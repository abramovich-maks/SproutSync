package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.infrastructure.s3aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
class AlbumDeleter {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;
    private final GroupFacade groupFacade;
    private final S3Service s3Service;

    public void deletePhoto(final Long groupId, final Long photoId) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);

        Album album = albumRepository.findByIdAndGroupId(photoId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Album with id:" + photoId + " not found in group:" + groupDto.groupId()));

        album.getAlbum().forEach(photo -> {
            if (photo.getUri() != null && !photo.getUri().isBlank()) {
                s3Service.deleteFile(photo.getUri());
            }
        });

        albumRepository.delete(album);
    }

    public void deleteUrl(final Long groupId, final Long albumId, final Long photoId) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);

        Album album = albumRepository.findByIdAndGroupId(albumId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Album with id:" + albumId + " not found in group:" + groupDto.groupId()));

        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new EntityNotFoundException("Photo with id:" + photoId + " not found"));

        if (photo.getAlbum() == null || !photo.getAlbum().getId().equals(album.getId())) {
            throw new EntityNotFoundException("Photo does not belong to album " + albumId);
        }

        String urlToBeDeleted = photo.getUri();
        if (urlToBeDeleted != null && !urlToBeDeleted.isBlank()) {
            s3Service.deleteFile(urlToBeDeleted);
        }

        album.removePhoto(photo);
        photoRepository.delete(photo);
        albumRepository.save(album);
    }
}

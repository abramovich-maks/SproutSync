package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumPhotoGroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.ListPhotosResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumGroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoResponseDto;
import lombok.AllArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;
    private final GroupFacade groupFacade;
    private final PhotoRepository photoRepository;


    PhotoAlbumGroupResponseDto getAllAlbumsByGroupId(final Long groupId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);

        List<PhotoAlbumResponseDto> albumShortInfoList = albumRepository.findAllByGroupId(groupId)
                .stream()
                .map(album -> PhotoAlbumResponseDto.builder()
                        .id(album.getId())
                        .description(album.getDescription())
                        .build())
                .toList();

        return PhotoAlbumGroupResponseDto.builder()
                .group(group)
                .album(albumShortInfoList)
                .build();
    }

    ListPhotosResponseDto getAllPhotosByAlbum(final Long groupId, final Long albumId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);

        Album album = albumRepository.findByIdAndGroupId(albumId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));


        List<PhotoResponseDto> photoList = photoRepository.findAllByAlbumId(album.getId())
                .stream()
                .map(photo -> PhotoResponseDto.builder()
                        .id(photo.getId())
                        .uri(photo.getUri())
                        .build())
                .toList();

        PhotoAlbumResponseDto photoAlbumResponse = PhotoAlbumResponseDto.builder()
                .id(album.getId())
                .description(album.getDescription())
                .build();

        return ListPhotosResponseDto.builder()
                .group(group)
                .album(photoAlbumResponse)
                .photo(photoList)
                .build();

    }

    AlbumPhotoGroupResponseDto getPhotoByIdAndAlbumId(final Long groupId, final Long albumId, final Long photoId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);

        Album album = albumRepository.findByIdAndGroupId(albumId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Album with id: " + albumId + " not found in group " + groupId));

        Photo photo = photoRepository.findByIdAndAlbumId(photoId, albumId)
                .orElseThrow(() -> new EntityNotFoundException("Photo with id: " + photoId + " not found in album " + albumId));

        PhotoResponseDto photoDto = PhotoResponseDto.builder()
                .id(photo.getId())
                .uri(photo.getUri())
                .build();

        PhotoAlbumResponseDto albumDto = PhotoAlbumResponseDto.builder()
                .id(album.getId())
                .description(album.getDescription())
                .build();

        return AlbumPhotoGroupResponseDto.builder()
                .group(group)
                .album(albumDto)
                .photo(photoDto)
                .build();
    }
}

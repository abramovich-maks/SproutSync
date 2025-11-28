package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumGroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoListDtoResponse;
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

    PhotoResponseDto getAllPhotosByAlbum(final Long groupId, final Long albumId) {
        GroupResponseDto group = groupFacade.getGroupById(groupId);

        Album album = albumRepository.findByIdAndGroupId(albumId, groupId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));


        List<PhotoListDtoResponse> photoList = photoRepository.findAllByAlbumId(album.getId())
                .stream()
                .map(photo -> PhotoListDtoResponse.builder()
                        .id(photo.getId())
                        .uri(photo.getUri())
                        .build())
                .toList();

        PhotoAlbumResponseDto photoAlbumResponse = PhotoAlbumResponseDto.builder()
                .id(album.getId())
                .description(album.getDescription())
                .build();

        return PhotoResponseDto.builder()
                .group(group)
                .album(photoAlbumResponse)
                .photo(photoList)
                .build();

    }
}

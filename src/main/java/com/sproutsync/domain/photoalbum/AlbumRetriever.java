package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumGroupResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;
    private final GroupFacade groupFacade;


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
}

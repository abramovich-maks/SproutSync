package com.sproutsync.infrastructure.photoalbum.controller;

import org.springframework.http.HttpStatus;

class AlbumMapper {

    public static DeleteResponseDto createDeletePhotoResponseDto(final Long albumId, final Long photoId) {
        return DeleteResponseDto.builder()
                .message("Photo with id [%d] in the album with id [%d] deleted successfully.".formatted(photoId, albumId))
                .status(HttpStatus.OK)
                .build();
    }

    public static DeleteResponseDto createDeleteAlbumResponse(final Long albumId) {
        return DeleteResponseDto.builder()
                .message("Album with id [%d] deleted successfully.".formatted(albumId))
                .status(HttpStatus.OK)
                .build();
    }
}

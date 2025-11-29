package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.photoalbum.dto.request.AlbumUploadRequestDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumPhotoGroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.ListPhotosResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumGroupResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AlbumFacade {

    private final AlbumUploader albumUploader;
    private final AlbumDeleter albumDeleter;
    private final AlbumRetriever albumRetriever;


    @Transactional
    public AlbumResponseDto uploadAlbum(Long groupId, AlbumUploadRequestDto uploadDto) {
        return albumUploader.uploadAlbum(groupId, uploadDto);
    }

    @Transactional
    public void deleteAlbum(Long groupId, Long albumId) {
        albumDeleter.deletePhoto(groupId, albumId);
    }

    @Transactional
    public void deletePhoto(Long groupId, Long albumId, Long photoId) {
        albumDeleter.deleteUrl(groupId, albumId, photoId);
    }

    public PhotoAlbumGroupResponseDto getAllAlbumsByGroupId(Long groupId) {
        return albumRetriever.getAllAlbumsByGroupId(groupId);
    }

    public ListPhotosResponseDto getAllPhotosByAlbum(Long groupId, Long albumId) {
        return albumRetriever.getAllPhotosByAlbum(groupId, albumId);
    }

    public AlbumPhotoGroupResponseDto getPhotoByIdAndAlbumId(Long groupId, Long albumId, Long photoId) {
        return albumRetriever.getPhotoByIdAndAlbumId(groupId, albumId, photoId);
    }
}
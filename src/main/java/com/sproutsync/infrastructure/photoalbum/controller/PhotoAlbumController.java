package com.sproutsync.infrastructure.photoalbum.controller;

import com.sproutsync.domain.photoalbum.AlbumFacade;
import com.sproutsync.domain.photoalbum.dto.request.AlbumUploadRequestDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumPhotoGroupResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.ListPhotosResponseDto;
import com.sproutsync.domain.photoalbum.dto.response.PhotoAlbumGroupResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sproutsync.infrastructure.photoalbum.controller.AlbumMapper.createDeleteAlbumResponse;
import static com.sproutsync.infrastructure.photoalbum.controller.AlbumMapper.createDeletePhotoResponseDto;

@Tag(name = "Albums", description = "Albums upload and management for groups")
@RestController
@RequestMapping("/api/groups/{groupId}/albums")
@RequiredArgsConstructor
public class PhotoAlbumController {

    private final AlbumFacade albumFacade;

    @Operation(summary = "Upload album", description = "Uploads a album to the specified group (multipart/form-data)")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AlbumResponseDto> uploadAlbum(@PathVariable Long groupId, @ModelAttribute AlbumUploadRequestDto requestDto) {
        AlbumResponseDto albumResponseDto = albumFacade.uploadAlbum(groupId, requestDto);
        return ResponseEntity.ok(albumResponseDto);
    }

    @Operation(summary = "List group albums", description = "Returns all photos for the specified group")
    @GetMapping()
    public ResponseEntity<PhotoAlbumGroupResponseDto> getAllAlbumsByGroup(@PathVariable Long groupId) {
        PhotoAlbumGroupResponseDto allAlbumsByGroupId = albumFacade.getAllAlbumsByGroupId(groupId);
        return ResponseEntity.ok(allAlbumsByGroupId);
    }

    @Operation(summary = "List album photos", description = "Returns all photos for the specified group")
    @GetMapping("/{albumId}")
    public ResponseEntity<ListPhotosResponseDto> getAllPhotosByAlbum(@PathVariable Long groupId, @PathVariable Long albumId) {
        ListPhotosResponseDto allPhotosByAlbum = albumFacade.getAllPhotosByAlbum(groupId, albumId);
        return ResponseEntity.ok(allPhotosByAlbum);
    }

    @Operation(summary = "Return photo by album", description = "Returns all photos for the specified group")
    @GetMapping("/{albumId}/{photoId}")
    public ResponseEntity<AlbumPhotoGroupResponseDto> getPhotoByIdAndAlbumId(@PathVariable Long groupId, @PathVariable Long albumId, @PathVariable Long photoId) {
        AlbumPhotoGroupResponseDto photoByIdAndAlbumId = albumFacade.getPhotoByIdAndAlbumId(groupId, albumId, photoId);
        return ResponseEntity.ok(photoByIdAndAlbumId);
    }

    @Operation(summary = "Delete album", description = "Deletes a album by ID for the specified group")
    @DeleteMapping("/{albumId}")
    public ResponseEntity<DeleteResponseDto> deleteAlbum(@PathVariable Long groupId, @PathVariable Long albumId) {
        albumFacade.deleteAlbum(groupId, albumId);
        DeleteResponseDto body = createDeleteAlbumResponse(albumId);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Delete photo", description = "Deletes a photo by ID for the specified group and album")
    @DeleteMapping("/{albumId}/{photoId}")
    public ResponseEntity<DeleteResponseDto> deletePhoto(@PathVariable Long groupId, @PathVariable Long albumId, @PathVariable Long photoId) {
        albumFacade.deletePhoto(groupId, albumId, photoId);
        DeleteResponseDto body = createDeletePhotoResponseDto(albumId, photoId);
        return ResponseEntity.ok(body);
    }
}

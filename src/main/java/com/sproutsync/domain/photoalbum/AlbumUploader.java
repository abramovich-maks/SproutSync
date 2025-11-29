package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.photoalbum.dto.request.AlbumUploadRequestDto;
import com.sproutsync.domain.photoalbum.dto.response.AlbumResponseDto;
import com.sproutsync.infrastructure.s3aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static com.sproutsync.domain.photoalbum.AlbumMapper.mapFromUserToUserDto;

@AllArgsConstructor
class AlbumUploader {

    private final AlbumRepository albumRepository;
    private final GroupFacade groupFacade;
    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final S3Service s3Service;

    @Transactional
    public AlbumResponseDto uploadAlbum(Long groupId, AlbumUploadRequestDto uploadDto) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);
        User user = loginAndRegisterFacade.getUserPrincipal();

        Group group = groupFacade.getGroupEntity(groupDto.groupId());

        Album album = new Album();
        album.setGroup(group);
        album.setDescription(uploadDto.description());
        album.setCreatedBy(user);

        uploadDto.file().forEach(multipartFile -> {
            try {
                String original = multipartFile.getOriginalFilename() == null ? "file" : multipartFile.getOriginalFilename();
                String key = java.util.UUID.randomUUID() + "-" + original;
                String url = s3Service.uploadFileAndGetUrl(multipartFile, key);

                Photo photo = new Photo();
                photo.setUri(url);
                album.addPhoto(photo);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file", e);
            }
        });

        Album saved = albumRepository.save(album);

        List<String> photoUrls = saved.getAlbum().stream()
                .map(Photo::getUri)
                .toList();

        return AlbumResponseDto.builder()
                .id(saved.getId())
                .group(groupDto)
                .photo(photoUrls)
                .description(saved.getDescription())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .createdBy(mapFromUserToUserDto(user))
                .build();
    }
}

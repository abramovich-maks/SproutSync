package com.sproutsync.domain.photo;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.photo.dto.request.PhotoUploadRequestDto;
import com.sproutsync.domain.photo.dto.response.PhotoResponseDto;
import com.sproutsync.infrastructure.s3aws.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static com.sproutsync.domain.photo.PhotoMapper.mapFromUserToUserDto;

@AllArgsConstructor
class PhotoUploader {

    private final PhotoRepository photoRepository;
    private final GroupFacade groupFacade;
    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final S3Service s3Service;

    @Transactional
    public PhotoResponseDto uploadPhoto(Long groupId, PhotoUploadRequestDto uploadDto) {
        GroupResponseDto groupDto = groupFacade.getGroupById(groupId);
        User user = loginAndRegisterFacade.getUserPrincipal();

        Group group = groupFacade.getGroupEntity(groupDto.groupId());

        Photo photo = new Photo();
        photo.setGroup(group);
        photo.setDescription(uploadDto.description());
        photo.setCreatedBy(user);

        uploadDto.file().forEach(multipartFile -> {
            try {
                String original = multipartFile.getOriginalFilename() == null ? "file" : multipartFile.getOriginalFilename();
                String key = java.util.UUID.randomUUID() + "-" + original;
                String url = s3Service.uploadFileAndGetUrl(multipartFile, key);

                PhotoUrl photoUrl = new PhotoUrl();
                photoUrl.setUrl(url);
                photo.addUrl(photoUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file", e);
            }
        });

        Photo saved = photoRepository.save(photo);

        List<String> urlList = saved.getUrl().stream()
                .map(PhotoUrl::getUrl)
                .toList();

        return PhotoResponseDto.builder()
                .id(saved.getId())
                .group(groupDto)
                .url(urlList)
                .description(saved.getDescription())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .createdBy(mapFromUserToUserDto(user))
                .build();
    }
}

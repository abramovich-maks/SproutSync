package com.sproutsync.domain.photo;

import com.sproutsync.domain.photo.dto.request.PhotoUploadRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    Photo uploadPhoto(MultipartFile file, Long groupId, PhotoUploadRequestDto uploadDto, String uploaderEmail) ;

    void deletePhoto(Long groupId, Long photoId);

    List<Photo> getAllPhotosByGroupId(Long idGroup);

}
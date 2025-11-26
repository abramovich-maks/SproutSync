package com.sproutsync.domain.photo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Schema(name = "PhotoUploadRequest", description = "Request payload for uploading a photo")
public record PhotoUploadRequestDto(

        Set<MultipartFile> file,

        @Schema(description = "Optional description of the photo", example = "Własna praca")
        String description
) {
}
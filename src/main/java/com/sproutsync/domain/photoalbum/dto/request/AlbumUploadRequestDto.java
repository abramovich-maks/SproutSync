package com.sproutsync.domain.photoalbum.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Schema(name = "AlbumUploadRequest", description = "Request payload for uploading a album")
public record AlbumUploadRequestDto(

        Set<MultipartFile> file,

        @Schema(description = "Optional description of the album", example = "Własna praca")
        String description
) {
}
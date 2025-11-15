package com.sproutsync.domain.photo.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "PhotoUploadRequest", description = "Request payload for uploading a photo")
public class PhotoUploadRequestDto {

    @Schema(description = "Optional description of the photo", example = "Własna praca")
    private String description;
}
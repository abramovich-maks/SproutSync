package com.sproutsync.domain.group.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
@Schema(name = "GroupResponse", description = "Response DTO representing a group")
public class GroupCreateResponseDto {

    @Schema(description = "Unique ID of the group", example = "12")
    private Long id;

    @NotBlank
    @Schema(description = "Name of the group", example = "Biedronki")
    private String name;

    @NotBlank
    @Schema(description = "Short description of the group", example = "This is the preschool group for 3-4 year olds")
    private String description;

}

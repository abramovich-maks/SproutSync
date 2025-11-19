package com.sproutsync.domain.group.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "GroupCreateRequestDto", description = "Request payload for creating or updating a group")
public class GroupCreateRequestDto {

    @NotBlank
    @Schema(description = "Name of the group", example = "Biedronki")
    private String name;

    @NotBlank
    @Schema(description = "Short description of the group", example = "This is the preschool group for 3-4 year olds")
    private String description;
}

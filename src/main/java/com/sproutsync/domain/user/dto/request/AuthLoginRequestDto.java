package com.sproutsync.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "AuthLoginRequest", description = "Login payload with user credentials")
public class AuthLoginRequestDto {

    @Email
    @NotBlank
    @Schema(description = "User email", example = "kowalski@mail.com", format = "email")
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    @Schema(description = "User password", example = "Password_Example", minLength = 8, maxLength = 100)
    private String password;
}

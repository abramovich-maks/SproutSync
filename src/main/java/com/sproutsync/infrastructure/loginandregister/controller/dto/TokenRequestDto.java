package com.sproutsync.infrastructure.loginandregister.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MIN_SIZE;

public record TokenRequestDto(
        @NotNull(message = "{login.not.null}")
        @NotEmpty(message = "{login.not.empty}")
        @Email(message = "{login.email}")
        String login,

        @NotNull(message = "{password.not.null}")
        @NotEmpty(message = "{password.not.empty}")
        @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "{password.size}")
        String password
) {
}

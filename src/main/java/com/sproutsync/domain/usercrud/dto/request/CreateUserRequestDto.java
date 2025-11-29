package com.sproutsync.domain.usercrud.dto.request;

import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MIN_SIZE;

@Builder
public record CreateUserRequestDto(
        @NotNull(message = "{username.not.null}")
        @NotEmpty(message = "{username.not.empty}")
        String username,

        @NotNull(message = "{surname.not.null}")
        @NotEmpty(message = "{surname.not.empty}")
        String surname,

        @NotNull(message = "{email.not.null}")
        @NotEmpty(message = "{email.not.empty}")
        @Email(message = "{register.email}")
        String email,

        @NotNull(message = "{password.not.null}")
        @NotEmpty(message = "{password.not.empty}")
        @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "{password.size}")
        String password,

        Set<Long> roleIds
) {
}


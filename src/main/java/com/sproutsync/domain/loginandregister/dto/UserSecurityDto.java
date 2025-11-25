package com.sproutsync.domain.loginandregister.dto;

import com.sproutsync.domain.role.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserSecurityDto(
        Long userId,
        String username,
        String surname,
        String mail,
        String password,
        Set<String> roles
) {
}

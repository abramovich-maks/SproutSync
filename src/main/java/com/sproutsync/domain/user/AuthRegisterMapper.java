package com.sproutsync.domain.user;

import com.sproutsync.domain.user.dto.request.AuthRegisterRequestDto;
import com.sproutsync.domain.user.dto.response.AuthResponseDto;

import java.util.Set;

public class AuthRegisterMapper {

    private AuthRegisterMapper() {
    }

    public static User toEntity(AuthRegisterRequestDto dto, RoleRepository roleRepository) {
        Role defaultRole = roleRepository.findByName("ROLE_PARENT")
                .orElseThrow(() -> new RuntimeException("Default role ROLE_PARENT not found"));
        return new User(null, dto.getUsername(), dto.getSurname(), dto.getEmail(), dto.getPassword(), Set.of(defaultRole));
    }

    public static AuthResponseDto toDto(User saved) {
        return new AuthResponseDto(
                saved.getId(),
                saved.getUsername(),
                saved.getSurname(),
                saved.getEmail(),
                saved.getRoles().stream()
                        .map(role -> role.getName())
                        .findFirst()
                        .orElse("")
        );
    }
}

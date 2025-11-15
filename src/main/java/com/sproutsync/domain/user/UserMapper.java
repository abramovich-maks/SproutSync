package com.sproutsync.domain.user;

import com.sproutsync.domain.loginandregister.Role;
import com.sproutsync.domain.loginandregister.RoleRepository;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.user.dto.request.UserCreateRequestDto;
import com.sproutsync.domain.user.dto.response.UserResponseDto;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    public static User toEntity(UserCreateRequestDto dto, RoleRepository roleRepository) {
        Role defaultRole = roleRepository.findByName("ROLE_PARENT")
                .orElseThrow(() -> new RuntimeException("Default role ROLE_PARENT not found"));

        Set<Role> roles = Set.of(defaultRole);

        return new User(
                null,
                dto.getUsername(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getPassword(),
                roles
        );
    }



    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());

        Set<String> roleNames = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleNames.add(role.getName());
        }
        dto.setRoles(roleNames);

        return dto;
    }

}

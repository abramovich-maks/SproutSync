package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.dto.RoleResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserResponseDto;

import java.util.Set;
import java.util.stream.Collectors;

class UserMapper {

    public static UserResponseDto mapFromUserToUserResponseDto(final User user) {
        Set<String> userRoles = user.getAuthorities().stream().map(Role::getName).collect(Collectors.toSet());
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .surname(user.getSurname())
                .email(user.getEmail())
                .roles(userRoles)
                .build();
    }

    public static Set<Role> mapFromRoleResponseDtoToRole(final Set<RoleResponseDto> roleResponseDtos) {
        return roleResponseDtos.stream()
                .map(role -> new Role(role.roleId(), role.role()))
                .collect(Collectors.toSet());
    }
}

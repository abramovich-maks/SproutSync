package com.sproutsync.infrastructure.user.controller;

import com.sproutsync.domain.usercrud.dto.response.UserListResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserResponseDto;
import com.sproutsync.infrastructure.user.controller.dto.UserControllerResponseDto;

import java.util.List;

class UserMapper {

    static UserControllerResponseDto mapFromUserResponseDtoToUserControllerResponseDto(UserResponseDto userResponseDto) {
        return UserControllerResponseDto.builder()
                .id(userResponseDto.id())
                .username(userResponseDto.username())
                .surname(userResponseDto.surname())
                .email(userResponseDto.email())
                .roles(userResponseDto.roles())
                .build();
    }

    public static List<UserControllerResponseDto> mapFromUserListResponseDtoToUserControllerResponseDto(final UserListResponseDto all) {
        return all.users().stream().map(user -> UserControllerResponseDto.builder()
                .id(user.id())
                .username(user.username())
                .surname(user.surname())
                .email(user.email())
                .roles(user.roles())
                .build()).toList();
    }
}

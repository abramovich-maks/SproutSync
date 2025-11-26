package com.sproutsync.domain.photo;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.dto.UserDto;

public class PhotoMapper {

    public static UserDto mapFromUserToUserDto(final User user) {
        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .surname(user.getSurname())
                .mail(user.getEmail())
                .build();
    }
}



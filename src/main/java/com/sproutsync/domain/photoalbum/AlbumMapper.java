package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.dto.UserDto;

class AlbumMapper {

    public static UserDto mapFromUserToUserDto(final User user) {
        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .surname(user.getSurname())
                .mail(user.getEmail())
                .build();
    }
}



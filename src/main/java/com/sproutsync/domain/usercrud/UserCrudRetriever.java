package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.usercrud.dto.response.UserListResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.sproutsync.domain.usercrud.UserMapper.mapFromUserToUserResponseDto;

@AllArgsConstructor
@Log4j2
class UserCrudRetriever {

    private final UserCrudRepository userCrudRepository;

    public UserListResponseDto findAll() {
        List<User> allUsers = userCrudRepository.findAll();

        List<UserResponseDto> dtos = allUsers.stream()
                .map(UserMapper::mapFromUserToUserResponseDto)
                .toList();

        return UserListResponseDto.builder()
                .users(dtos)
                .message("All users fetched successfully")
                .build();
    }

    public UserResponseDto findById(Long id) {
        User user = userCrudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapFromUserToUserResponseDto(user);
    }

    public UserResponseDto findByEmail(String email) {
        User user = userCrudRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapFromUserToUserResponseDto(user);
    }

    public void checkUserExists(final Long userId) {
        if (!userCrudRepository.existsById(userId)) {
            log.warn("User with id [{}] not found", userId);
            throw new RuntimeException("User with id [" + userId + "] not found");
        }
    }

    public boolean userExists(final String userEmail) {
        if (userCrudRepository.existsByEmail(userEmail)) {
            log.warn("User with email [{}] already exists", userEmail);
            throw new RuntimeException("User with email [" + userEmail + "] already exists");
        }
        return false;
    }
}

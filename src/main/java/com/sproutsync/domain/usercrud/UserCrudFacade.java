package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.usercrud.dto.request.CreateUserRequestDto;
import com.sproutsync.domain.usercrud.dto.request.UserUpdateRequestDto;
import com.sproutsync.domain.usercrud.dto.response.UserListResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserUpdateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCrudFacade {

    private final UserCrudRetriever userCrudRetriever;
    private final UserDeleter userDeleter;
    private final UserUpdater userUpdater;
    private final UserAdder userAdder;

    public RegisterUserResponseDto addUser(CreateUserRequestDto user) {
        return userAdder.addUser(user);
    }

    public UserUpdateResponseDto partiallyUpdateUser(Long userId, UserUpdateRequestDto updateDto) {
        return userUpdater.partiallyUpdateUser(userId, updateDto);
    }

    public void deleteUserById(Long userId) {
        userDeleter.deleteUserById(userId);
    }

    public UserResponseDto findByEmail(String email) {
        return userCrudRetriever.findByEmail(email);
    }

    public UserResponseDto findUserById(Long userId) {
        return userCrudRetriever.findById(userId);
    }

    public UserListResponseDto findAll() {
        return userCrudRetriever.findAll();
    }
}

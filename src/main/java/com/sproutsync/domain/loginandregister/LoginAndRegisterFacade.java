package com.sproutsync.domain.loginandregister;


import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRetriever userRetriever;
    private final UserAdder userAdder;

    public UserDto findByEmail(String email) {
        return userRetriever.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        return userRetriever.findAllUsers();
    }

    public RegisterUserResponseDto register(final RegisterUserRequestDto user) {
        return userAdder.register(user);
    }

}

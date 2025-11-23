package com.sproutsync.domain.loginandregister;


import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import com.sproutsync.domain.loginandregister.dto.UserSecurityDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRetriever userRetriever;
    private final UserAdder userAdder;

    public UserSecurityDto findByEmail(String email) {
        return userRetriever.findByEmail(email);
    }

    public RegisterUserResponseDto register(final RegisterUserRequestDto user) {
        return userAdder.register(user);
    }

    public UserDto findUserDtoByEmail(String email) {
        return userRetriever.findUserDtoByEmail(email);
    }

    public User getUserPrincipal() {
        return userRetriever.getUserPrincipal();
    }
}

package com.sproutsync.infrastructure.security.jwt;

import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
class UserDetailsService implements UserDetailsManager {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(final String username) throws BadCredentialsException {
        UserDto userDto = loginAndRegisterFacade.findByEmail(username);
        return getUser(userDto);
    }

    @Override
    public void createUser(final UserDetails user) {

    }

    @Override
    public void updateUser(final UserDetails user) {

    }

    @Override
    public void deleteUser(final String username) {

    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {

    }

    @Override
    public boolean userExists(final String username) {
        return false;
    }

    private User getUser(UserDto user) {
        return new User(
                user.mail(),
                user.password(),
                user.roles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}

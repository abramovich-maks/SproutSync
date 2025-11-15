package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
class UserAdder {

    private final UserRepository userRepository;

    RegisterUserResponseDto register(final RegisterUserRequestDto user) {
        if (userExists(user.email())) {
            log.warn("User with email: {} already exists", user.email());
            throw new UserAlreadyExistException(user.email());
        }
        User createdUser = User.builder()
                .email(user.email())
                .password(user.password())
                .build();
        User savedUser = userRepository.save(createdUser);
        log.info("Saved user with id: {}", savedUser.getId());
        return RegisterUserResponseDto
                .builder()
                .email(createdUser.getEmail())
                .message("Success. User created.")
                .build();
    }

    public boolean userExists(final String username) {
        return userRepository.existsByEmail(username);
    }
}


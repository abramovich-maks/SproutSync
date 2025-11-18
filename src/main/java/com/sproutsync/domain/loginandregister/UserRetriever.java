package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
class UserRetriever {

    private final UserRepository userRepository;

    UserDto findByEmail(final String email) {
        User userByEmail = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User with email: {} not found", email);
                    return new BadCredentialsException(email);
                });
        return UserDto.builder()
                .userId(userByEmail.getId())
                .mail(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .build();
    }

    public UserDto findById(Long id) {
        User retrievedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.builder()
                .userId(retrievedUser.getId())
                .username(retrievedUser.getUsername())
                .surname(retrievedUser.getSurname())
                .mail(retrievedUser.getEmail())
                .build();
    }

    public boolean userExists(final String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }
}
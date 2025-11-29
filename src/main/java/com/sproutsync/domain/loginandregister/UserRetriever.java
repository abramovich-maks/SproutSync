package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.loginandregister.dto.UserDto;
import com.sproutsync.domain.loginandregister.dto.UserSecurityDto;
import com.sproutsync.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
class UserRetriever {

    private final UserRepository userRepository;

    UserSecurityDto findByEmail(final String email) {
        User userByEmail = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User with email: {} not found", email);
                    return new BadCredentialsException(email);
                });

        Set<String> roleNames = userByEmail.getAuthorities()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return UserSecurityDto.builder()
                .userId(userByEmail.getId())
                .mail(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .roles(roleNames)
                .build();
    }

    public UserDto findUserDtoByEmail(String email) {
        User retrievedUser = userRepository.findFirstByEmail(email)
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

    public User getUserPrincipal() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
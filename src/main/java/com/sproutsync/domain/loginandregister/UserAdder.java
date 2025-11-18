package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.RoleFacade;
import com.sproutsync.domain.role.dto.RoleResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.sproutsync.domain.loginandregister.LoginAndRegisterMapper.mapFromRoleResponseDtoToRole;

@AllArgsConstructor
@Log4j2
@Service
class UserAdder {

    private final UserRepository userRepository;
    private final UserRetriever userRetriever;
    private final RoleFacade roleFacade;

    RegisterUserResponseDto register(final RegisterUserRequestDto user) {
        if (userRetriever.userExists(user.email())) {
            log.warn("User with email: {} already exists", user.email());
            throw new UserAlreadyExistException(user.email());
        }
        RoleResponseDto defaultRole = roleFacade.findRoleByName("ROLE_PARENT");
        Role role = mapFromRoleResponseDtoToRole(defaultRole);
        Set<Role> roles = Set.of(role);

        User createdUser = User.builder()
                .username(user.username())
                .surname(user.surname())
                .email(user.email())
                .password(user.password())
                .authorities(roles)
                .build();
        User savedUser = userRepository.save(createdUser);
        log.info("Saved user with id: {}", savedUser.getId());
        return RegisterUserResponseDto
                .builder()
                .username(createdUser.getUsername())
                .surname(createdUser.getSurname())
                .email(createdUser.getEmail())
                .message("Success. User created.")
                .build();
    }
}


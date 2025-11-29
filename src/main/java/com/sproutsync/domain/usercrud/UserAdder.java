package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.loginandregister.UserAlreadyExistException;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.RoleFacade;
import com.sproutsync.domain.role.dto.RoleResponseDto;
import com.sproutsync.domain.usercrud.dto.request.CreateUserRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.sproutsync.domain.usercrud.UserMapper.mapFromRoleResponseDtoToRole;
import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
@Log4j2
class UserAdder {

    private final PasswordEncoder passwordEncoder;
    private final UserCrudRetriever userCrudRetriever;
    private final UserCrudRepository userCrudRepository;
    private final RoleFacade roleFacade;

    public RegisterUserResponseDto addUser(CreateUserRequestDto user) {
        if (userCrudRetriever.userExists(user.email())) {
            throw new UserAlreadyExistException(user.email());
        }
        String encodedPassword = passwordEncoder.encode(user.password());
        Set<Long> roleIds = user.roleIds();
        if (roleIds == null || roleIds.isEmpty()) {
            roleIds = Set.of(3L);
        }
        Set<RoleResponseDto> rolesDtos = roleIds.stream()
                .map(roleFacade::findRoleById)
                .collect(toSet());
        Set<Role> roles = mapFromRoleResponseDtoToRole(rolesDtos);

        User createdUser = User.builder()
                .username(user.username())
                .surname(user.surname())
                .email(user.email())
                .password(encodedPassword)
                .authorities(roles)
                .build();

        User savedUser = userCrudRepository.save(createdUser);
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


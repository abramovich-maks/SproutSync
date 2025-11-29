package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.RoleFacade;
import com.sproutsync.domain.role.dto.RoleResponseDto;
import com.sproutsync.domain.usercrud.dto.request.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sproutsync.domain.usercrud.UserMapper.mapFromRoleResponseDtoToRole;

@AllArgsConstructor
class UserUpdater {

    private final UserCrudRepository userCrudRepository;
    private final UserCrudRetriever userCrudRetriever;
    private final RoleFacade roleFacade;
    private final PasswordEncoder passwordEncoder;

    public Map<String, Object> partiallyUpdateUser(Long id, UserUpdateRequestDto dto) {
        if (dto.username() == null && dto.surname() == null && dto.email() == null && dto.password() == null && dto.roleIds() == null) {
            throw new IllegalArgumentException("At least one field must be provided for update.");
        }
        User user = userCrudRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        Map<String, Object> updatedFields = new LinkedHashMap<>();
        updatedFields.put("id", id);

        if (dto.username() != null) {
            user.setUsername(dto.username());
            updatedFields.put("username", dto.username());
        }

        if (dto.surname() != null) {
            user.setSurname(dto.surname());
            updatedFields.put("surname", dto.surname());
        }

        if (dto.email() != null) {
            userCrudRetriever.userExists(dto.email());
            user.setEmail(dto.email());
            updatedFields.put("email", dto.email());
        }

        if (dto.password() != null) {
            user.setPassword(passwordEncoder.encode(dto.password()));
            updatedFields.put("passwordUpdated", true);
        }

        if (dto.roleIds() != null) {
            Set<RoleResponseDto> roleDtos = dto.roleIds().stream()
                    .map(roleFacade::findRoleById)
                    .collect(Collectors.toSet());

            Set<Role> roles = mapFromRoleResponseDtoToRole(roleDtos);
            user.setAuthorities(roles);

            updatedFields.put("roles", roles.stream().map(Role::getName).collect(Collectors.toSet()));
        }
        userCrudRepository.save(user);
        return updatedFields;
    }
}

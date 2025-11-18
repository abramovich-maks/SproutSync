package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.RoleFacade;
import com.sproutsync.domain.role.dto.RoleResponseDto;
import com.sproutsync.domain.usercrud.dto.request.UserUpdateRequestDto;
import com.sproutsync.domain.usercrud.dto.response.UserUpdateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sproutsync.domain.usercrud.UserMapper.mapFromRoleResponseDtoToRole;

@Service
@AllArgsConstructor
class UserUpdater {

    private final UserCrudRepository userCrudRepository;
    private final UserCrudRetriever userCrudRetriever;
    private final RoleFacade roleFacade;
    private final PasswordEncoder passwordEncoder;


    public UserUpdateResponseDto partiallyUpdateUser(Long id, UserUpdateRequestDto dto) {
        User user = userCrudRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserUpdateResponseDto.UserUpdateResponseDtoBuilder responseBuilder =
                UserUpdateResponseDto.builder().id(id);

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
            responseBuilder.username(dto.getUsername());
        }
        if (dto.getSurname() != null) {
            user.setSurname(dto.getSurname());
            responseBuilder.surname(dto.getSurname());
        }
        if (dto.getEmail() != null) {
            userCrudRetriever.userExists(dto.getEmail());
            user.setEmail(dto.getEmail());
            responseBuilder.email(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRoleIds() != null) {
            Set<RoleResponseDto> collectRoleId = dto.getRoleIds().stream()
                    .map(roleFacade::findRoleById)
                    .collect(Collectors.toSet());
            Set<Role> collect = mapFromRoleResponseDtoToRole(collectRoleId);
            user.setAuthorities(collect);
            responseBuilder.roleIds(dto.getRoleIds());
        }
        userCrudRepository.save(user);
        return responseBuilder.build();
    }
}

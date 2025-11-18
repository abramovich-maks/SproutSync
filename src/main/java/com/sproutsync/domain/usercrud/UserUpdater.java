package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.Role;
import com.sproutsync.domain.loginandregister.RoleRepository;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.usercrud.dto.request.UserUpdateRequestDto;
import com.sproutsync.domain.usercrud.dto.response.UserUpdateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class UserUpdater {

    private final UserCrudRepository userCrudRepository;
    private final UserCrudRetriever userCrudRetriever;
    private final RoleRepository roleRepository;
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
        //TODO use roleFacade
        if (dto.getRoleIds() != null) {
            Set<Role> roles = dto.getRoleIds().stream()
                    .map(roleId -> roleRepository.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("role not found")))
                    .collect(Collectors.toSet());
            user.setAuthorities(roles);
            responseBuilder.roleIds(dto.getRoleIds());
        }
        userCrudRepository.save(user);
        return responseBuilder.build();
    }
}

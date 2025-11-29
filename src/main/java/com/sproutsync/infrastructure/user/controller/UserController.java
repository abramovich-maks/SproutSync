package com.sproutsync.infrastructure.user.controller;

import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import com.sproutsync.domain.usercrud.UserCrudFacade;
import com.sproutsync.domain.usercrud.dto.request.CreateUserRequestDto;
import com.sproutsync.domain.usercrud.dto.request.UserUpdateRequestDto;
import com.sproutsync.domain.usercrud.dto.response.UserListResponseDto;
import com.sproutsync.domain.usercrud.dto.response.UserResponseDto;
import com.sproutsync.infrastructure.user.controller.dto.UserControllerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.sproutsync.infrastructure.user.controller.UserMapper.mapFromUserListResponseDtoToUserControllerResponseDto;
import static com.sproutsync.infrastructure.user.controller.UserMapper.mapFromUserResponseDtoToUserControllerResponseDto;

@Tag(name = "Users", description = "CRUD operations for users")
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserCrudFacade userCrudFacade;

    @Operation(summary = "List all users", description = "Returns a list of all users")
    @GetMapping
    public ResponseEntity<List<UserControllerResponseDto>> findAll() {
        UserListResponseDto allUsers = userCrudFacade.findAll();
        List<UserControllerResponseDto> body = mapFromUserListResponseDtoToUserControllerResponseDto(allUsers);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Get user by ID", description = "Returns a single user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserControllerResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userById = userCrudFacade.findUserById(id);
        UserControllerResponseDto body = mapFromUserResponseDtoToUserControllerResponseDto(userById);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Get user by email", description = "Returns a single user by their email")
    @GetMapping("/{email}")
    public ResponseEntity<UserControllerResponseDto> findByEmail(@PathVariable String email) {
        UserResponseDto byEmail = userCrudFacade.findByEmail(email);
        UserControllerResponseDto body = mapFromUserResponseDtoToUserControllerResponseDto(byEmail);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Create a new user", description = "Creates and returns a new user")
    @PostMapping
    public ResponseEntity<RegisterUserResponseDto> createUser(@RequestBody @Valid CreateUserRequestDto dto) {
        RegisterUserResponseDto user = userCrudFacade.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Update user", description = "Updates an existing user by ID")
    @PatchMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long userId, @RequestBody @Valid UserUpdateRequestDto dto) {
        Map<String, Object> response = userCrudFacade.partiallyUpdateUser(userId, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user", description = "Deletes a user by ID")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userCrudFacade.deleteUserById(userId);
    }
}
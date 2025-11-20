package com.sproutsync.infrastructure.loginandregister.controller;

import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
class RegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Operation(summary = "Register a new user", description = "Creates a new account")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerNewUser(@Valid @RequestBody RegisterUserRequestDto requestDto) {
        RegisterUserResponseDto registeredUser = loginAndRegisterFacade.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}

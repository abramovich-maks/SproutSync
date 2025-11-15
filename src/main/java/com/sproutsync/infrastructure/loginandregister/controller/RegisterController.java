package com.sproutsync.infrastructure.loginandregister.controller;

import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.domain.loginandregister.dto.RegisterUserRequestDto;
import com.sproutsync.domain.loginandregister.dto.RegisterUserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
class RegisterController {

    LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptpasswordEncoder;

    @Operation(summary = "Register a new user", description = "Creates a new account")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerNewUser(@Valid @RequestBody RegisterUserRequestDto requestDto) {
        String encodedPassword = bCryptpasswordEncoder.encode(requestDto.password());
        RegisterUserResponseDto register = loginAndRegisterFacade.register(new RegisterUserRequestDto(requestDto.email(), encodedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }
}

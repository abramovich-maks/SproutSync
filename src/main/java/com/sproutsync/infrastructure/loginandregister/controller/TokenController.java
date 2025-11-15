package com.sproutsync.infrastructure.loginandregister.controller;

import com.sproutsync.infrastructure.loginandregister.controller.dto.JwtResponseDto;
import com.sproutsync.infrastructure.loginandregister.controller.dto.TokenRequestDto;
import com.sproutsync.infrastructure.security.jwt.JwtAuthenticator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
class TokenController {

    private final JwtAuthenticator jwtAuthenticator;

    @Operation(summary = "Login", description = "Verifies user credentials and returns a JWT token as plain text.")
    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> getToken(@Valid @RequestBody TokenRequestDto requestDto) {
        final JwtResponseDto jwtResponseDto = jwtAuthenticator.authenticateAndGenerateToken(requestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }
}

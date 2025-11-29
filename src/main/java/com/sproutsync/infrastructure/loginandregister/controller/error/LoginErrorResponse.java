package com.sproutsync.infrastructure.loginandregister.controller.error;

import org.springframework.http.HttpStatus;

public record LoginErrorResponse(
        String message,
        HttpStatus status
) {
}

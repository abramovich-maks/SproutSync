package com.sproutsync.infrastructure.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorDto(
        List<String> message,
        HttpStatus status
) {
}

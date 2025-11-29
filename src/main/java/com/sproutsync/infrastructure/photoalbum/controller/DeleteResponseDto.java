package com.sproutsync.infrastructure.photoalbum.controller;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteResponseDto(String message, HttpStatus status) {
}

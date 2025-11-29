package com.sproutsync.infrastructure.meal.controller;

import org.springframework.http.HttpStatus;

public record DeleteMenuResponseDto(String message, HttpStatus status) {
}

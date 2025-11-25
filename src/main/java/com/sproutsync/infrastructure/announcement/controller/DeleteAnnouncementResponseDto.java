package com.sproutsync.infrastructure.announcement.controller;

import org.springframework.http.HttpStatus;

public record DeleteAnnouncementResponseDto(String message, HttpStatus status) {
}

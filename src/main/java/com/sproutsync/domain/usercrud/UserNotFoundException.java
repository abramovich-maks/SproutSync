package com.sproutsync.domain.usercrud;

public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long userId) {
        super(String.format("User with id [%d] not found", userId));
        this.userId = userId;
    }
}
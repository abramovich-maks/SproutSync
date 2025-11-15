package com.sproutsync.domain.loginandregister;

public class UserAlreadyExistException extends RuntimeException {

    public final String userEmail;

    public UserAlreadyExistException(String userEmail) {
        super(String.format("eMail [%s] already exists", userEmail));
        this.userEmail = userEmail;
    }
}

package com.sproutsync.infrastructure.loginandregister.controller.error;

import com.sproutsync.domain.loginandregister.UserAlreadyExistException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
class TokenControllerErrorHandler {

    private static final String BAD_CREDENTIALS = "Bad credentials";

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public LoginErrorResponse handleBadCredentials() {
        return new LoginErrorResponse(BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public LoginErrorResponse mailDuplicate(UserAlreadyExistException duplicateMailException) {
        final String message = String.format("eMail [%s] already exists", duplicateMailException.userEmail);
        log.error(message);
        return new LoginErrorResponse(message, HttpStatus.CONFLICT);
    }
}

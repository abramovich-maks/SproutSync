package com.sproutsync.domain.user;

import com.sproutsync.domain.user.dto.request.AuthLoginRequestDto;
import com.sproutsync.domain.user.dto.request.AuthRegisterRequestDto;
import com.sproutsync.domain.user.dto.response.AuthResponseDto;

public interface AuthService {

    AuthResponseDto register(AuthRegisterRequestDto user);

    String verify(AuthLoginRequestDto user);
}

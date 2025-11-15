package com.sproutsync.domain.user;

import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.domain.user.dto.request.UserUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(String id);

    User create(User user);

    User update(String id, UserUpdateRequestDto dto);

    void delete(String id);

    Optional<User> findByEmail(String email);
}

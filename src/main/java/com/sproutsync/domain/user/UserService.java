package com.sproutsync.domain.user;

import com.sproutsync.domain.user.dto.request.UserUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User create(User user);

    User update(Long id, UserUpdateRequestDto dto);

    void delete(Long id);

    Optional<User> findByEmail(String email);
}

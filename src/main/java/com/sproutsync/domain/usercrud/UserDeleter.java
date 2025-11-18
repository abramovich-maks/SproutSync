package com.sproutsync.domain.usercrud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserDeleter {

    private final UserCrudRepository userCrudRepository;
    private final UserCrudRetriever userCrudRetriever;

    public void deleteUserById(Long userId) {
        userCrudRetriever.checkUserExists(userId);
        userCrudRepository.deleteById(userId);
    }
}

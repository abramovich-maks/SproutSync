package com.sproutsync.domain.usercrud;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserDeleter {

    private final UserCrudRepository userCrudRepository;
    private final UserCrudRetriever userCrudRetriever;

    public void deleteUserById(Long userId) {
        userCrudRetriever.checkUserExists(userId);
        userCrudRepository.deleteById(userId);
    }
}

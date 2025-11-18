package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserCrudRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

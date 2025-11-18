package com.sproutsync.domain.loginandregister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    boolean existsByEmail(String email);
}


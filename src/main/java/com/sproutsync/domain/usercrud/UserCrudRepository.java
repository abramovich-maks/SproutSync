package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.loginandregister.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface UserCrudRepository extends JpaRepository<User, Long> {

    @Query("""
    select distinct u
    from User u
    left join fetch u.authorities r
    left join fetch u.group g
    where u.email = :email
""")
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Override
    @EntityGraph(attributePaths = "authorities")
    List<User> findAll();
}

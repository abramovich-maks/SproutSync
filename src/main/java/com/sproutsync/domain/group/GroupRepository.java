package com.sproutsync.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findGroupById(Long id);

    boolean existsGroupById(Long id);
}

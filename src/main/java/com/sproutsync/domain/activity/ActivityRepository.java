package com.sproutsync.domain.activity;

import com.sproutsync.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByGroup(Group group);

    Optional<Activity> findByGroupIdAndId(Long group_id, Long id);

    Activity findByGroupIdAndDateTime(Long groupId, LocalDateTime dateTime);
}


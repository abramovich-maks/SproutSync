package com.sproutsync.domain.announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllByGroupId(Long groupId);

    Optional<Announcement> findByGroupIdAndId(Long groupId, Long id);
}

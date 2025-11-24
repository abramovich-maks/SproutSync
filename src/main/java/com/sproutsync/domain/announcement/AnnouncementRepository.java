package com.sproutsync.domain.announcement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllByGroupId(Long groupId);

    @Query("select a from Announcement a join fetch a.createdBy where a.group.id = :groupId AND a.id = :id")
    Optional<Announcement> findByGroupIdAndId(Long groupId, Long id);
}

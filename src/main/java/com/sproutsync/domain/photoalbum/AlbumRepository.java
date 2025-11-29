package com.sproutsync.domain.photoalbum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByGroupId(Long groupId);

    Optional<Album> findByIdAndGroupId(Long albumId, Long groupId);

    void deleteByIdAndGroupId(Long id, Long groupId);

    List<Album> findAllByGroupId(Long groupId);
}
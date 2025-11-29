package com.sproutsync.domain.photoalbum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface PhotoRepository extends JpaRepository<Photo,Long> {

    List<Photo> findAllByAlbumId(Long albumId);

    Optional<Photo> findByIdAndAlbumId(Long photoId, Long albumId);
}

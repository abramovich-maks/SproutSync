package com.sproutsync.domain.photoalbum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PhotoRepository extends JpaRepository<Photo,Long> {

    List<Photo> findAllByAlbumId(Long albumId);
}

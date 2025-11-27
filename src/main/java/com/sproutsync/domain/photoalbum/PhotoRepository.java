package com.sproutsync.domain.photoalbum;

import org.springframework.data.jpa.repository.JpaRepository;

interface PhotoRepository extends JpaRepository<Photo,Long> {

}

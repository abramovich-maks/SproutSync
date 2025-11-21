package com.sproutsync.domain.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AllergenRepository extends JpaRepository<Allergen,Long> {
}

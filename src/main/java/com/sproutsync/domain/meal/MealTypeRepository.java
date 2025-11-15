package com.sproutsync.domain.meal;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MealTypeRepository extends JpaRepository<MealType,Long> {

    Optional<MealType> findByName(String name);}

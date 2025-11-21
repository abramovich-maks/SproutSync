package com.sproutsync.domain.meal;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

interface MealTypeRepository extends JpaRepository<MealType,Long> {

    Optional<MealType> findByName(String name);}

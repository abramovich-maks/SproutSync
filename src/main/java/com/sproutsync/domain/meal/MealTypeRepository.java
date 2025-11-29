package com.sproutsync.domain.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

interface MealTypeRepository extends JpaRepository<MealType, Long> {

    List<MealType> findAllByNameIn(Set<String> names);}

package com.sproutsync.domain.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface MenuDayRepository extends JpaRepository<MenuDay, Long> {

    Optional<MenuDay> findByGroupIdAndId(Long group_id, Long id);

    List<MenuDay> getAllByGroupId(Long group);

    @Query("SELECT m FROM MenuDay m " +
            "LEFT JOIN FETCH m.meals ml " +
            "LEFT JOIN FETCH ml.mealType " +
            "LEFT JOIN FETCH m.allergens a " +
            "WHERE m.group.id = :groupId AND m.date = :date")
   Optional<MenuDay> findByGroupIdAndDate(Long groupId, LocalDate date);
}

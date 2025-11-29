package com.sproutsync.domain.meal;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@AllArgsConstructor
@Transactional
class MenuDeleter {

    private final MenuDayRepository menuDayRepository;

    public void deleteMenuDay(Long groupId, LocalDate date) {
        menuDayRepository.findByGroupIdAndDate(groupId, date)
                .ifPresentOrElse(menuDayRepository::delete, () -> {
                    throw new MenuDayNotFoundException(date);
                });
    }
}

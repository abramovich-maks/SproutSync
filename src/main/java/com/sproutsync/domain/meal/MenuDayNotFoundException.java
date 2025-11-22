package com.sproutsync.domain.meal;

import java.time.LocalDate;

public class MenuDayNotFoundException extends RuntimeException {
    private LocalDate date;

    public MenuDayNotFoundException(LocalDate date) {
        super(String.format("Menu for the day [%s] not found", date));
        this.date = date;
    }
}
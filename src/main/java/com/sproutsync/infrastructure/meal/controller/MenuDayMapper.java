package com.sproutsync.infrastructure.meal.controller;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

class MenuDayMapper {

    public static DeleteMenuResponseDto getDeleteMenuResponseDto(LocalDate date) {
        return new DeleteMenuResponseDto("Menu for date %s deleted successfully.".formatted(date), HttpStatus.OK);
    }
}

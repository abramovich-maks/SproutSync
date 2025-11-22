package com.sproutsync.domain.meal.dto.request;

import java.time.LocalDate;

public record MenuDayRequestDto(
        Long groupId,
        LocalDate date
) {
}

package com.sproutsync.domain.meal;

class MealTypeNotFoundException extends RuntimeException {
    private final String type;

    public MealTypeNotFoundException(final String type) {
        super(String.format("Meal type [%s] not found", type));
        this.type = type;
    }
}
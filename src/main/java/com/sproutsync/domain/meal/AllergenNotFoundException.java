package com.sproutsync.domain.meal;

import java.util.Set;

public class AllergenNotFoundException extends RuntimeException {
    private final Set<Long> ids;

    public AllergenNotFoundException(Set<Long> ids) {
        super(String.format("Allergens not found with ids: %s", ids));
        this.ids = ids;
    }
}
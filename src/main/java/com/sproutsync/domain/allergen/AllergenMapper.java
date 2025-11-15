package com.sproutsync.domain.allergen;

import com.sproutsync.domain.meal.dto.response.AllergenDto;


public class AllergenMapper {

    private AllergenMapper() {
    }

    public static AllergenDto toDto(Allergen allergen) {
        AllergenDto allergenDto = new AllergenDto();
        allergenDto.setId(allergen.getId());
        allergenDto.setName(allergen.getName());
        return allergenDto;
    }
}

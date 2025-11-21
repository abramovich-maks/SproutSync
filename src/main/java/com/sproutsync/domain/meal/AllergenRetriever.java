package com.sproutsync.domain.meal;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class AllergenRetriever {

    private final AllergenRepository allergenRepository;

    public Set<Allergen> getAllergensByIds(final Set<Long> allergenIds) {
        List<Allergen> foundAllergens = allergenRepository.findAllById(allergenIds);

        if (foundAllergens.size() != allergenIds.size()) {
            Set<Long> foundIds = foundAllergens.stream()
                    .map(Allergen::getId)
                    .collect(Collectors.toSet());

            Set<Long> missingIds = allergenIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());

            throw new AllergenNotFoundException(missingIds);
        }

        return new HashSet<>(foundAllergens);
    }
}
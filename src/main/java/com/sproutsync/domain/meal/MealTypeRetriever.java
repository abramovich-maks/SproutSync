package com.sproutsync.domain.meal;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
class MealTypeRetriever {

    private final MealTypeRepository mealTypeRepository;

    public Map<String, MealType> getMealTypesMapByNames(Set<String> names) {
        Map<String, MealType> foundTypes = mealTypeRepository.findAllByNameIn(names).stream()
                .collect(Collectors.toMap(MealType::getName, Function.identity()));
        for (String name : names) {
            if (!foundTypes.containsKey(name)) {
                throw new MealTypeNotFoundException(name);
            }
        }
        return foundTypes;
    }
}
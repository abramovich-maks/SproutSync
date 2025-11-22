package com.sproutsync.domain.meal;

import com.sproutsync.domain.group.GroupFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MenyDayConfiguration {

    @Bean
    public static MenuDayFacade menuDayFacade(GroupFacade groupFacade,
                                              MenuDayRepository menuDayRepository,
                                              AllergenRepository allergenRepository,
                                              MealTypeRepository mealTypeRepository
    ) {
        AllergenRetriever allergenRetriever = new AllergenRetriever(allergenRepository);
        MealTypeRetriever mealTypeRetriever = new MealTypeRetriever(mealTypeRepository);
        MenuDayAdder menuDayAdder = new MenuDayAdder(groupFacade, allergenRetriever, mealTypeRetriever, menuDayRepository);
        MenuRetriever menuRetriever = new MenuRetriever(menuDayRepository, groupFacade);
        MenuDeleter menuDeleter = new MenuDeleter(menuDayRepository);
        return new MenuDayFacade(menuDayAdder, menuRetriever, menuDeleter);
    }
}

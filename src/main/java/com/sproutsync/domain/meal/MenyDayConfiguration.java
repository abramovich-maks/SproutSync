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
        MenuDayAdder menuDayAdder = new MenuDayAdder(groupFacade,menuDayRepository,allergenRepository,mealTypeRepository);
        return new MenuDayFacade(menuDayAdder);
    }
}

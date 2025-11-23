package com.sproutsync.domain.announcement;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AnnouncementConfiguration {

    @Bean
    AnnouncementFacade announcementFacade(AnnouncementRepository announcementRepository,
                                          GroupFacade groupFacade,
                                          LoginAndRegisterFacade loginAndRegisterFacade) {
        AnnouncementAdder announcementAdder = new AnnouncementAdder(announcementRepository, groupFacade, loginAndRegisterFacade);
        return new AnnouncementFacade(announcementAdder);
    }
}

package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.role.RoleFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoginAndRegisterConfiguration {

    @Bean
    public static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository, RoleFacade roleFacade) {
        UserRetriever userRetriever = new UserRetriever(userRepository);
        UserAdder userAdder = new UserAdder(userRepository, userRetriever, roleFacade);
        return new LoginAndRegisterFacade(userRetriever, userAdder);
    }
}

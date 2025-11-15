package com.sproutsync.domain.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoginAndRegisterConfiguration {

    @Bean
    public static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository) {
        UserRetriever userRetriever = new UserRetriever(userRepository);
        UserAdder userAdder = new UserAdder(userRepository);
        return new LoginAndRegisterFacade(userRetriever, userAdder);
    }
}

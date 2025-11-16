package com.sproutsync.domain.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoginAndRegisterConfiguration {

    @Bean
    public static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository, RoleRepository roleRepository) {
        UserRetriever userRetriever = new UserRetriever(userRepository);
        UserAdder userAdder = new UserAdder(userRepository, roleRepository);
        return new LoginAndRegisterFacade(userRetriever, userAdder);
    }
}

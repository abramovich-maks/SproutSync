package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.role.RoleFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class LoginAndRegisterConfiguration {

    @Bean
    public static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository userRepository, RoleFacade roleFacade, PasswordEncoder passwordEncoder) {
        UserRetriever userRetriever = new UserRetriever(userRepository);
        UserAdder userAdder = new UserAdder(userRepository, userRetriever, roleFacade, passwordEncoder);
        return new LoginAndRegisterFacade(userRetriever, userAdder);
    }
}

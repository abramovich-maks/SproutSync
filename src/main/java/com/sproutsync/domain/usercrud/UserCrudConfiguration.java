package com.sproutsync.domain.usercrud;

import com.sproutsync.domain.role.RoleFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserCrudConfiguration {

    @Bean
    UserCrudFacade userCrudFacade(UserCrudRepository userCrudRepository, RoleFacade roleFacade, PasswordEncoder passwordEncoder) {
        UserCrudRetriever userCrudRetriever = new UserCrudRetriever(userCrudRepository);
        UserDeleter userDeleter = new UserDeleter(userCrudRepository, userCrudRetriever);
        UserUpdater userUpdater = new UserUpdater(userCrudRepository, userCrudRetriever, roleFacade, passwordEncoder);
        UserAdder userAdder = new UserAdder(passwordEncoder, userCrudRetriever, userCrudRepository, roleFacade);
        return new UserCrudFacade(userCrudRetriever, userDeleter, userUpdater, userAdder);
    }
}

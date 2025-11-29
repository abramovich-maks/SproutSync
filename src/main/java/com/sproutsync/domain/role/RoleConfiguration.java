package com.sproutsync.domain.role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RoleConfiguration {

    @Bean
    public static RoleFacade roleFacade(RoleRepository roleRepository) {
        RoleRetriever roleRetriever = new RoleRetriever(roleRepository);
        return new RoleFacade(roleRetriever);
    }
}

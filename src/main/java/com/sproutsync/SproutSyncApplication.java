package com.sproutsync;

import com.sproutsync.infrastructure.security.jwt.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfigurationProperties.class})
public class SproutSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SproutSyncApplication.class, args);
    }

}

package com.sproutsync.domain.photo;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.infrastructure.s3aws.S3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PhotoConfiguration {

    @Bean
    PhotoFacade photoFacade(PhotoRepository photoRepository,
                            GroupFacade groupFacade,
                            LoginAndRegisterFacade loginAndRegisterFacade,
                            S3Service s3Service
    ) {
        PhotoUploader photoUploader = new PhotoUploader(photoRepository, groupFacade, loginAndRegisterFacade, s3Service);
        return new PhotoFacade(photoUploader);
    }
}

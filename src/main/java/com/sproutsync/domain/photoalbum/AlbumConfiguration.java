package com.sproutsync.domain.photoalbum;

import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.loginandregister.LoginAndRegisterFacade;
import com.sproutsync.infrastructure.s3aws.S3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AlbumConfiguration {

    @Bean
    AlbumFacade albumFacade(AlbumRepository albumRepository,
                            GroupFacade groupFacade,
                            LoginAndRegisterFacade loginAndRegisterFacade,
                            S3Service s3Service,
                            PhotoRepository photoRepository
    ) {

        AlbumUploader albumUploader = new AlbumUploader(albumRepository, groupFacade, loginAndRegisterFacade, s3Service);
        AlbumDeleter albumDeleter = new AlbumDeleter(albumRepository, photoRepository, groupFacade, s3Service);
        return new AlbumFacade(albumUploader, albumDeleter);
    }
}

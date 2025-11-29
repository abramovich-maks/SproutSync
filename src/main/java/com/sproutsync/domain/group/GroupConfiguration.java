package com.sproutsync.domain.group;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GroupConfiguration {

    @Bean
    GroupFacade groupFacade(GroupRepository groupRepository) {
        GroupAdder groupAdder = new GroupAdder(groupRepository);
        GroupRetriever groupRetriever = new GroupRetriever(groupRepository);
        GroupUpdater groupUpdater = new GroupUpdater(groupRetriever, groupRepository);
        GroupDeleter groupDeleter = new GroupDeleter(groupRepository, groupRetriever);
        return new GroupFacade(groupAdder, groupUpdater, groupDeleter, groupRetriever);
    }
}
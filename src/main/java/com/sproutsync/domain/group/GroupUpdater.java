package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.request.GroupUpdateRequestDto;
import com.sproutsync.domain.group.dto.response.GroupUpdateResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
class GroupUpdater {

    private final GroupRetriever groupRetriever;
    private final GroupRepository groupRepository;

    public GroupUpdateResponseDto updateGroup(Long groupId, GroupUpdateRequestDto requestDto) {
        groupRetriever.checkGroupExists(groupId);
        Group groupById = groupRetriever.findGroupById(groupId);
        GroupUpdateResponseDto.GroupUpdateResponseDtoBuilder responseDtoBuilder = GroupUpdateResponseDto.builder().groupId(groupId);

        if (requestDto.name() != null && !requestDto.name().isBlank()) {
            groupById.setName(requestDto.name());
            responseDtoBuilder.groupName(requestDto.name());
            log.info("Group with id [{}] updated name for '{}'", groupId, requestDto.name());
        }

        if (requestDto.description() != null && !requestDto.description().isBlank()) {
            groupById.setDescription(requestDto.description());
            responseDtoBuilder.description(requestDto.description());
            log.info("Group with id [{}] updated description for '{}'", groupId, requestDto.description());
        }
        groupRepository.save(groupById);
        return responseDtoBuilder.build();
    }
}

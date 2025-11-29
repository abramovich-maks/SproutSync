package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.response.DeleteGroupResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
class GroupDeleter {

    private final GroupRepository groupRepository;
    private final GroupRetriever groupRetriever;

    public DeleteGroupResponseDto deleteGroupById(Long groupId) {
        groupRetriever.checkGroupExists(groupId);
        groupRepository.deleteById(groupId);
        return DeleteGroupResponseDto.builder()
                .message(String.format("Group with id:[{%d}] has been deleted", groupId))
                .status(HttpStatus.OK)
                .build();
    }
}

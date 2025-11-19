package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.request.GroupCreateRequestDto;
import com.sproutsync.domain.group.dto.request.GroupUpdateRequestDto;
import com.sproutsync.domain.group.dto.response.DeleteGroupResponseDto;
import com.sproutsync.domain.group.dto.response.GroupCreateResponseDto;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.group.dto.response.GroupUpdateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
public class GroupFacade {

    private final GroupAdder groupAdder;
    private final GroupUpdater groupUpdater;
    private final GroupDeleter groupDeleter;
    private final GroupRetriever groupRetriever;

    public GroupCreateResponseDto createGroup(GroupCreateRequestDto requestDto) {
        return groupAdder.createGroup(requestDto);
    }

    public GroupUpdateResponseDto updateGroup(Long groupId, GroupUpdateRequestDto requestDto) {
        return groupUpdater.updateGroup(groupId, requestDto);
    }

    public DeleteGroupResponseDto deleteGroup(Long groupId) {
        return groupDeleter.deleteGroupById(groupId);
    }

    public List<GroupResponseDto> getAllGroups(Pageable pageable) {
        return groupRetriever.findAllGroup(pageable);
    }

    public GroupResponseDto getGroupById(Long groupId) {
        return groupRetriever.findGroupDtoById(groupId);
    }

    // todo add method addUserToGroup
}

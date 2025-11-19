package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.request.GroupCreateRequestDto;
import com.sproutsync.domain.group.dto.response.GroupCreateResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class GroupAdder {

    private final GroupRepository groupRepository;

    public GroupCreateResponseDto createGroup(GroupCreateRequestDto requestDto) {
        Group newGroup = Group.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();
        Group savedGroup = groupRepository.save(newGroup);
        return GroupCreateResponseDto.builder()
                .id(savedGroup.getId())
                .name(savedGroup.getName())
                .description(savedGroup.getDescription())
                .build();
    }
}

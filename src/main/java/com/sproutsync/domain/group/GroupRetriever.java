package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class GroupRetriever {

    private final GroupRepository groupRepository;

    public Group findGroupById(Long groupId) {
        return groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    public GroupResponseDto findGroupDtoById(Long groupId) {
        Group group = groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        return GroupResponseDto.builder()
                .groupId(group.getId())
                .groupName(group.getName())
                .description(group.getDescription())
                .build();
    }

    public void checkGroupExists(Long groupId) {
        if (!groupRepository.existsGroupById(groupId)) {
            throw new GroupNotFoundException(groupId);
        }
    }

    List<GroupResponseDto> findAllGroup(Pageable pageable) {
        return groupRepository.findAll(pageable)
                .stream().map(group -> GroupResponseDto.builder()
                        .groupId(group.getId())
                        .groupName(group.getName())
                        .description(group.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public Group getGroupEntity(Long groupId) {
        return groupRepository.findGroupById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
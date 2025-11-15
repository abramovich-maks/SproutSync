package com.sproutsync.domain.group;

import com.sproutsync.domain.group.dto.request.GroupRequestDto;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;

import java.util.Collections;

public class GroupMapper {

    private GroupMapper() {}

    public static Group toEntity(GroupRequestDto dto) {
        return new Group(null, dto.getName(), dto.getDescription(), dto.getMainFoto(), Collections.emptyList());
    }

    public static GroupResponseDto toGroupDto(Group group) {
        GroupResponseDto dto = new GroupResponseDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setMainFoto(group.getMainFoto());
        return dto;
    }
}

package com.sproutsync.domain.accessrequest;

import com.sproutsync.domain.accessrequest.dto.response.AccessResponseDto;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.loginandregister.User;
import com.sproutsync.userservice.util.AccessStatus;

import java.time.LocalDateTime;

public class AccessRequestMapper {

    private AccessRequestMapper() {
    }

    public static AccessRequest toEntity(User user, Group group) {
        return new AccessRequest(
                null,
                user,
                group,
                AccessStatus.PENDING,
                LocalDateTime.now(),
                null);
    }

    public static AccessResponseDto toDto(AccessRequest accessRequest) {
        AccessResponseDto dto = new AccessResponseDto();
        dto.setId(accessRequest.getId());
        dto.setUserId(accessRequest.getParent().getId());
        dto.setUserName(accessRequest.getParent().getUsername());
        dto.setUserSurname(accessRequest.getParent().getSurname());
        dto.setGroupId(accessRequest.getGroup().getId());
        dto.setAccessStatus(accessRequest.getAccessStatus());
        return dto;
    }
}

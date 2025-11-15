package com.sproutsync.domain.activity;

import com.sproutsync.domain.activity.dto.request.ActivityCreateRequestDto;
import com.sproutsync.domain.activity.dto.response.ActivityResponseDto;
import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.loginandregister.User;

public class ActivityMapper {

    private ActivityMapper() {
    }

    public static Activity toEntity(ActivityCreateRequestDto dto, Group group, User user) {
        return new Activity(
                null,
                group,
                dto.getDateTime(),
                dto.getActivities(),
                null,
                null,
                user
        );

    }
    public static ActivityResponseDto toDto(Activity entity) {
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();
        activityResponseDto.setId(entity.getId());
        activityResponseDto.setGroupId(entity.getGroup().getId());
        activityResponseDto.setDateTime(entity.getDateTime());
        activityResponseDto.setActivities(entity.getActivities());
        activityResponseDto.setCreatedAt(entity.getCreatedAt());
        activityResponseDto.setUpdatedAt(entity.getUpdatedAt());
        activityResponseDto.setCreatedBy(entity.getCreatedBy().getId());
        return activityResponseDto;
    }
}

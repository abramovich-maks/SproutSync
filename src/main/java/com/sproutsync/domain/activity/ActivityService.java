package com.sproutsync.domain.activity;

import com.sproutsync.domain.activity.dto.request.ActivityUpdateRequestDto;


import java.util.List;
import java.util.Optional;

public interface ActivityService {

    Activity createActivity(Long groupId, Activity activity);

    Activity updateActivity(Long group_id, Long id, ActivityUpdateRequestDto activityUpdateRequestDto);

    void deleteActivity(Long groupId, Long activityId);

    Optional<Activity> getActivityByGroup(Long groupId, Long activityId);

    List<Activity> getAllActivitiesByGroupId(Long groupId);

}

package com.sproutsync.domain.accessrequest;

import com.sproutsync.userservice.util.AccessStatus;

import java.util.List;
import java.util.Optional;

public interface AccessRequestService {

    AccessRequest createRequest(String email, Long groupId);

    AccessRequest updateRequestStatus(Long id, AccessStatus status);

    Optional<AccessRequest> findById(Long id);

    List<AccessRequest> findRequestsByParentId(Long parentId);

    List<AccessRequest> findRequestsByGroupId(Long groupId);

    List<AccessRequest> findAllByStatus(String statusRaw);

    Optional<AccessRequest> findByUserAndGroup(Long userId, Long groupId);

}

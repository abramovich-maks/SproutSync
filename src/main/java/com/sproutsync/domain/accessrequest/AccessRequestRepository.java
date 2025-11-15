package com.sproutsync.domain.accessrequest;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.user.User;
import com.sproutsync.userservice.util.AccessStatus;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {

    List<AccessRequest> findAllByParent(User parent);

    List<AccessRequest> findAllByGroup(Group group);

    List<AccessRequest> findAllByAccessStatus(AccessStatus status);

    Optional<AccessRequest> findByParentIdAndGroupId(Long userId, Long groupId);
}


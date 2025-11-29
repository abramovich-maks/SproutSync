package com.sproutsync.domain.group;

public class GroupNotFoundException extends RuntimeException {

    private final Long groupId;

    public GroupNotFoundException(final Long groupId) {
        super(String.format("Group with [%d] not found", groupId));
        this.groupId = groupId;
    }
}
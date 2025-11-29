package com.sproutsync.infrastructure.group.controller;


import com.sproutsync.domain.group.GroupFacade;
import com.sproutsync.domain.group.dto.request.GroupCreateRequestDto;
import com.sproutsync.domain.group.dto.request.GroupUpdateRequestDto;
import com.sproutsync.domain.group.dto.response.DeleteGroupResponseDto;
import com.sproutsync.domain.group.dto.response.GroupCreateResponseDto;
import com.sproutsync.domain.group.dto.response.GroupResponseDto;
import com.sproutsync.domain.group.dto.response.GroupUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Groups", description = "CRUD operations for groups")
@RestController
@AllArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    public final GroupFacade groupFacade;

    @Operation(summary = "Create group", description = "Creates a new group")
    @PostMapping
    public ResponseEntity<GroupCreateResponseDto> createGroup(@RequestBody @Valid GroupCreateRequestDto requestDto) {
        GroupCreateResponseDto group = groupFacade.createGroup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @Operation(summary = "Update group", description = "Updates an existing group by ID")
    @PutMapping("/{groupId}")
    public ResponseEntity<GroupUpdateResponseDto> updateGroup(@PathVariable Long groupId, @RequestBody @Valid GroupUpdateRequestDto responseDto) {
        GroupUpdateResponseDto updatedGroup = groupFacade.updateGroup(groupId, responseDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedGroup);
    }

    @Operation(summary = "Delete group", description = "Deletes a group by ID")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<DeleteGroupResponseDto> deleteGroup(@PathVariable Long groupId) {
        DeleteGroupResponseDto deleteGroupResponseDto = groupFacade.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteGroupResponseDto);
    }

    @Operation(summary = "List all groups", description = "Returns all groups")
    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> getAllGroups(Pageable pageable) {
        List<GroupResponseDto> allGroups = groupFacade.getAllGroups(pageable);
        return ResponseEntity.ok().body(allGroups);
    }

    @Operation(summary = "Get group by ID", description = "Returns a group by its ID")
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponseDto> getGroupById(@PathVariable Long groupId) {
        GroupResponseDto groupById = groupFacade.getGroupById(groupId);
        return ResponseEntity.ok().body(groupById);
    }
}

package com.sproutsync.infrastructure.announcement.controller;

import com.sproutsync.domain.announcement.AnnouncementFacade;
import com.sproutsync.domain.announcement.dto.request.AnnouncementCreateRequestDto;
import com.sproutsync.domain.announcement.dto.request.AnnouncementUpdateRequestDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementCreateResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementRetrieveResponseDto;
import com.sproutsync.domain.announcement.dto.response.AnnouncementUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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

import static com.sproutsync.infrastructure.announcement.controller.AnnouncementMapper.getDeleteAnnouncementResponseDto;

@Tag(name = "Announcements", description = "CRUD operations for group announcements")
@RestController
@AllArgsConstructor
@RequestMapping("/api/groups/{idGroup}/announce")
class AnnounceController {

    private final AnnouncementFacade announcementFacade;

    @Operation(summary = "Create announcement", description = "Creates a new announcement for a given group")
    @PostMapping
    public ResponseEntity<AnnouncementCreateResponseDto> createAnnouncement(@PathVariable Long idGroup, @RequestBody @Valid AnnouncementCreateRequestDto announcementCreateDtoRequestDto) {
        AnnouncementCreateResponseDto announcement = announcementFacade.createAnnouncement(idGroup, announcementCreateDtoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(announcement);
    }

    @Operation(summary = "Update announcement", description = "Updates an existing announcement by ID for a given group")
    @PutMapping("/{announceId}")
    public ResponseEntity<AnnouncementUpdateResponseDto> updateAnnouncement(@PathVariable Long idGroup, @PathVariable Long announceId, @RequestBody @Valid AnnouncementUpdateRequestDto updateDto) {
        AnnouncementUpdateResponseDto announcementUpdateResponseDto = announcementFacade.updateAnnouncement(idGroup, announceId, updateDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(announcementUpdateResponseDto);
    }

    @Operation(summary = "Delete announcement", description = "Deletes an announcement by ID for a given group")
    @DeleteMapping("/{announcementId}")
    public ResponseEntity<DeleteAnnouncementResponseDto> deleteAnnouncement(@PathVariable Long idGroup, @PathVariable Long announcementId) {
        announcementFacade.deleteAnnouncement(idGroup, announcementId);
        DeleteAnnouncementResponseDto body = getDeleteAnnouncementResponseDto(announcementId);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Get announcement by ID", description = "Returns an announcement by ID for a given group")
    @GetMapping("/{announceId}")
    public ResponseEntity<AnnouncementRetrieveResponseDto> getAnnouncementByGroupId(@PathVariable Long idGroup, @PathVariable Long announceId) {
        AnnouncementRetrieveResponseDto announcementByGroup = announcementFacade.getAnnouncementByGroup(idGroup, announceId);
        return ResponseEntity.ok(announcementByGroup);
    }

    @Operation(summary = "List all announcements", description = "Returns all announcements for a given group")
    @GetMapping
    public ResponseEntity<List<AnnouncementRetrieveResponseDto>> getAllAnnouncementsByGroupId(@PathVariable Long idGroup) {
        List<AnnouncementRetrieveResponseDto> allAnnouncementsByGroupId = announcementFacade.getAllAnnouncementsByGroupId(idGroup);
        return ResponseEntity.ok(allAnnouncementsByGroupId);
    }
}

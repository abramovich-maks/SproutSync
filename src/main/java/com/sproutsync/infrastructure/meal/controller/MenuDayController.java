package com.sproutsync.infrastructure.meal.controller;


import com.sproutsync.domain.meal.MenuDayFacade;
import com.sproutsync.domain.meal.dto.response.MenuDayUpdateResponseDto;
import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;
import com.sproutsync.domain.meal.dto.request.MenuDayUpdateDto;
import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import com.sproutsync.domain.meal.dto.response.MenuDayResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static com.sproutsync.infrastructure.meal.controller.MenuDayMapper.getDeleteMenuResponseDto;

@Tag(name = "Menu", description = "CRUD operations for group menus")
@RestController
@AllArgsConstructor
@RequestMapping("/api/groups/{groupId}/menu")
class MenuDayController {

    private final MenuDayFacade menuDayFacade;

    @Operation(summary = "Create menu", description = "Creates a new menu for the given group")
    @PostMapping
    public ResponseEntity<MenuDayCreateDtoResponse> createMenuDay(@PathVariable Long groupId, @RequestBody @Valid MenuDayCreateDtoRequest menuDayCreateDto) {
        MenuDayCreateDtoResponse menuDay = menuDayFacade.createMenuDay(groupId, menuDayCreateDto);
        return ResponseEntity.ok(menuDay);
    }


    @Operation(summary = "Update menu", description = "Updates an existing menu by ID for a given group")
    @PatchMapping("/{date}")
    public ResponseEntity<MenuDayUpdateResponseDto> updateMenuDay(@PathVariable Long groupId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody MenuDayUpdateDto menuDayDto) {
        MenuDayUpdateResponseDto menuDayUpdateResponseDto = menuDayFacade.updateMenuDay(groupId, date, menuDayDto);
        return ResponseEntity.ok(menuDayUpdateResponseDto);
    }

    @Operation(summary = "Delete menu", description = "Deletes a menu by ID for a given group")
    @DeleteMapping("/{date}")
    public ResponseEntity<DeleteMenuResponseDto> deleteMenuDayByGroup(@PathVariable Long groupId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        menuDayFacade.deleteMenuDay(groupId, date);
        DeleteMenuResponseDto body = getDeleteMenuResponseDto(date);
        return ResponseEntity.ok(body);
    }

    @Operation(summary = "List all menus", description = "Returns all menus for a given group")
    @GetMapping
    public ResponseEntity<List<MenuDayResponseDto>> getAllMenusByGroup(@PathVariable Long groupId) {
        List<MenuDayResponseDto> allMenuByGroupId = menuDayFacade.getAllMenuByGroupId(groupId);
        return ResponseEntity.ok(allMenuByGroupId);
    }

    @Operation(summary = "Find menu by date", description = "Finds a menu by date for a given group")
    @GetMapping("/date/{date}")
    public ResponseEntity<MenuDayResponseDto> findMenuByDate(@PathVariable Long groupId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        MenuDayResponseDto menuByData = menuDayFacade.findMenuByData(groupId, date);
        return ResponseEntity.ok(menuByData);
    }
}

package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.MenuDayCreateDtoRequest;
import com.sproutsync.domain.meal.dto.request.MenuDayUpdateDto;
import com.sproutsync.domain.meal.dto.response.MenuDayCreateDtoResponse;
import com.sproutsync.domain.meal.dto.response.MenuDayResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Transactional
public class MenuDayFacade {

    private final MenuDayAdder menuDayAdder;
    private final MenuRetriever menuRetriever;
    private final MenuDeleter menuDeleter;
    private final MenuUpdater menuUpdater;

    public MenuDayCreateDtoResponse createMenuDay(Long groupId, MenuDayCreateDtoRequest menuDayRequest) {
        return menuDayAdder.createMenuDay(groupId, menuDayRequest);
    }

    public MenuDayUpdateResponseDto updateMenuDay(Long groupId, LocalDate date, MenuDayUpdateDto menuDayDto) {
        return menuUpdater.partUpdateMenuDay(groupId, date, menuDayDto);
    }

    public void deleteMenuDay(Long groupId, LocalDate date) {
        menuDeleter.deleteMenuDay(groupId, date);
    }

    public MenuDayResponseDto findMenuByData(Long groupId, LocalDate date) {
        return menuRetriever.findMenuForTheDay(groupId, date);
    }

    public List<MenuDayResponseDto> getAllMenuByGroupId(final Long groupId) {
        return menuRetriever.findAllMenuByGroup(groupId);
    }
}

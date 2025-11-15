package com.sproutsync.domain.meal;

import com.sproutsync.domain.meal.dto.request.MenuDayUpdateDto;

import java.time.LocalDate;
import java.util.List;

public interface MenuDayService {

    MenuDay createMenuDay(Long groupId, MenuDay menuDay);

    MenuDay updateMenuDay(Long groupId, Long menuId, MenuDayUpdateDto menuDayUpdateDto);

    void deleteMenuDay(Long groupId, Long id);

    MenuDay getMenuDayByGroupId(Long groupId, Long menuId);

    List<MenuDay> getAllMenuByGroupId(Long groupId);

    MenuDay getMenuDayByData(Long groupId, LocalDate date);


}

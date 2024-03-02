package com.project.hotel.services.admin.rooms;

import com.project.hotel.dtos.RoomDto;
import com.project.hotel.dtos.responses.RoomsResponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);

    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomById(Long id);

    boolean updateRoom(Long id, RoomDto roomDto);

    void deleteRoom(Long id);
}

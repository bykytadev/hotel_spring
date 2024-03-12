package com.project.hotel.services.customer.room;

import com.project.hotel.dtos.responses.RoomsResponseDto;

public interface RoomService {

    RoomsResponseDto getAvailableRooms(int pageNumber);

}

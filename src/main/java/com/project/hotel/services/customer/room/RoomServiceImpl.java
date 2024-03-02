package com.project.hotel.services.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotel.dtos.responses.RoomsResponseDto;
import com.project.hotel.entites.Room;
import com.project.hotel.repositories.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    public static final int SEARCH_RESULT_PER_PAGE = 6;

    private final RoomRepository roomRepository;

    @Override
    public RoomsResponseDto getAvailableRooms(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Room> roomPage = roomRepository.findByAvailable(true, paging);
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        return roomsResponseDto;
    }

}

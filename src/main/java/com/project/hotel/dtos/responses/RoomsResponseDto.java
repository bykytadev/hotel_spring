package com.project.hotel.dtos.responses;

import java.util.List;

import com.project.hotel.dtos.RoomDto;

import lombok.Data;

@Data
public class RoomsResponseDto {

    private List<RoomDto> roomDtoList;

    private Integer totalPages;

    private Integer pageNumber;

}

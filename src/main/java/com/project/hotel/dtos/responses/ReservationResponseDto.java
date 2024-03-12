package com.project.hotel.dtos.responses;

import java.util.List;

import com.project.hotel.dtos.ReservationDto;

import lombok.Data;

@Data
public class ReservationResponseDto {

    private List<ReservationDto> reservationDtoList;

    private Integer totalPages;

    private Integer pageNumber;

}

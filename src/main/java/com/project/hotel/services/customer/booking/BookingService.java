package com.project.hotel.services.customer.booking;

import com.project.hotel.dtos.ReservationDto;
import com.project.hotel.dtos.responses.ReservationResponseDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);

    ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);

}

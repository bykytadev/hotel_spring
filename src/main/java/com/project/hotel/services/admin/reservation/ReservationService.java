package com.project.hotel.services.admin.reservation;

import com.project.hotel.dtos.responses.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);

    boolean changeReservationStatus(Long id, String status);
}

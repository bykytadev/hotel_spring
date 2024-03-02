package com.project.hotel.services.admin.reservation;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotel.dtos.responses.ReservationResponseDto;
import com.project.hotel.entites.Reservation;
import com.project.hotel.entites.Room;
import com.project.hotel.entites.enums.ReservationStatus;
import com.project.hotel.repositories.ReservationRepository;
import com.project.hotel.repositories.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    public static final int SEARCH_RESULT_PER_PAGE = 4;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponseDto getAllReservations(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAll(paging);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;
    }

    @Override
    public boolean changeReservationStatus(Long id, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            if (Objects.equals(status, "Approve")) {
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
                Optional<Room> optionalRoom = roomRepository.findById(optionalReservation.get().getRoom().getId());
                if (optionalRoom.isPresent()){
                    Room existingRoom = optionalRoom.get();
                    existingRoom.setAvailable(false);
                    roomRepository.save(existingRoom);
                }
            } else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }

}

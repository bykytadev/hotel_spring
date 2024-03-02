package com.project.hotel.services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotel.dtos.ReservationDto;
import com.project.hotel.dtos.responses.ReservationResponseDto;
import com.project.hotel.entites.Reservation;
import com.project.hotel.entites.Room;
import com.project.hotel.entites.User;
import com.project.hotel.entites.enums.ReservationStatus;
import com.project.hotel.repositories.ReservationRepository;
import com.project.hotel.repositories.RoomRepository;
import com.project.hotel.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    public static final int SEARCH_RESULT_PER_PAGE = 4;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());
        if (optionalRoom.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setUser(optionalUser.get());
            reservation.setRoom(optionalRoom.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAllByUserId(paging,userId);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;
    }

}

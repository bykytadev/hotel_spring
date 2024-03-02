package com.project.hotel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.entites.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    Page<Reservation> findAllByUserId(Pageable paging, Long userId);
}

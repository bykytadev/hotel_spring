package com.project.hotel.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.entites.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findByAvailable(boolean b, Pageable paging);
}
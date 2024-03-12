package com.project.hotel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.entites.User;
import com.project.hotel.entites.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);
}

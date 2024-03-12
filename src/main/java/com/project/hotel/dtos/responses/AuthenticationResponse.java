package com.project.hotel.dtos.responses;

import com.project.hotel.entites.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private UserRole userRole;

}

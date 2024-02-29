package com.project.hotel.dtos;

import com.project.hotel.entites.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}

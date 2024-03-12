package com.project.hotel.dtos.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;

}


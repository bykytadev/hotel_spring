package com.project.hotel.services.auth;

import com.project.hotel.dtos.UserDto;
import com.project.hotel.dtos.requests.SignupRequest;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest) throws Exception;

    Boolean hasUserWithEmail(String email);

}

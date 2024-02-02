package com.ghirr.LuxeRide.services.auth;

import com.ghirr.LuxeRide.dto.SignupRequest;
import com.ghirr.LuxeRide.dto.UserDto;

public interface AuthService {

    UserDto create(SignupRequest signupRequest);
    boolean hasCostumerWithEmail(String email);
}

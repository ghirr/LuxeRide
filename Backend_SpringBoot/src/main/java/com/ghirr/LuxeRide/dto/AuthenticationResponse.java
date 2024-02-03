package com.ghirr.LuxeRide.dto;

import com.ghirr.LuxeRide.enums.UserRole;

public record AuthenticationResponse(
        String jwt,

        UserRole userRole,

        Integer userId

) {
}

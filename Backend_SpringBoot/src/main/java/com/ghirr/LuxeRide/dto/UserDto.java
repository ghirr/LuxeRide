package com.ghirr.LuxeRide.dto;

import com.ghirr.LuxeRide.enums.UserRole;

public record UserDto(
        Integer id,

        String name,

        String email,
        UserRole userRole

) {


}

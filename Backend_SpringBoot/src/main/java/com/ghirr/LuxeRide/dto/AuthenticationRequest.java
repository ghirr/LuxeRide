package com.ghirr.LuxeRide.dto;

public record AuthenticationRequest(
        String email,

        String password
) {
}

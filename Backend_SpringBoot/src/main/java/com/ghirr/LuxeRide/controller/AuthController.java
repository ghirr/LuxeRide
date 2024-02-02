package com.ghirr.LuxeRide.controller;

import com.ghirr.LuxeRide.dto.SignupRequest;
import com.ghirr.LuxeRide.dto.UserDto;
import com.ghirr.LuxeRide.services.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<?> signupCostumer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCostumerWithEmail(signupRequest.email()))
            return new ResponseEntity<>("Costumer aready exist " +
                "with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdUser=authService.create(signupRequest);
        if(createdUser ==null)
            return new ResponseEntity<>("Costumer Not created," +
                "please came later", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
}

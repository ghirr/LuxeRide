package com.ghirr.LuxeRide.controller;

import com.ghirr.LuxeRide.dto.AuthenticationRequest;
import com.ghirr.LuxeRide.dto.AuthenticationResponse;
import com.ghirr.LuxeRide.dto.SignupRequest;
import com.ghirr.LuxeRide.dto.UserDto;
import com.ghirr.LuxeRide.entity.User;
import com.ghirr.LuxeRide.repository.UserRepository;
import com.ghirr.LuxeRide.services.auth.AuthService;
import com.ghirr.LuxeRide.services.jwt.UserService;
import com.ghirr.LuxeRide.utils.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;

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

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
        throws BadCredentialsException,
            DisabledException,
            UsernameNotFoundException{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(),authenticationRequest.password()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.email());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = null;
        if(optionalUser.isPresent()){
            authenticationResponse=new AuthenticationResponse(jwt,optionalUser.get().getUserRole(), optionalUser.get().getId());
        }
        
        return authenticationResponse;

    }

}

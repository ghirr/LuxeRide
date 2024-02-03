package com.ghirr.LuxeRide.services.auth;

import com.ghirr.LuxeRide.dto.SignupRequest;
import com.ghirr.LuxeRide.dto.UserDto;
import com.ghirr.LuxeRide.entity.User;
import com.ghirr.LuxeRide.enums.UserRole;
import com.ghirr.LuxeRide.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public UserDto create(SignupRequest signupRequest) {
        User user=new User();
        System.out.println(signupRequest);
        user.setEmail(signupRequest.email());
        user.setName(signupRequest.name());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.password()));
        user.setUserRole(UserRole.Costumer);

        User CreatedUser=userRepository.save(user);

        return new UserDto(CreatedUser.getId(), CreatedUser.getName(), CreatedUser.getEmail(), CreatedUser.getUserRole());
    }

    @Override
    public boolean hasCostumerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}

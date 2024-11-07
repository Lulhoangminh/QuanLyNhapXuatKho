package com.example.bill_management.services;

import com.example.bill_management.dto.requests.AuthenticationRequest;
import com.example.bill_management.dto.responses.AuthenticationResponse;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECAuthentication;
import com.example.bill_management.exceptions.ECUser;
import com.example.bill_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse login(AuthenticationRequest request){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));

        boolean isValidPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValidPassword){
            throw new AppException(ECAuthentication.UNAUTHENTICATED);
        }

        return AuthenticationResponse.builder()
                .isValid(true)
                .message("Login Success")
                .build();
    }
}

package com.example.bill_management.controllers;

import com.example.bill_management.dto.requests.AuthenticationRequest;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.AuthenticationResponse;
import com.example.bill_management.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }
}

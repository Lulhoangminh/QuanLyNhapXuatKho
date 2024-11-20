package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}

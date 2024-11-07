package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationRequest {
    private String username;
    private String password;
    private String name;
}

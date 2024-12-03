package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String name;

    @Builder.Default
    private boolean isDeleted = false;
}

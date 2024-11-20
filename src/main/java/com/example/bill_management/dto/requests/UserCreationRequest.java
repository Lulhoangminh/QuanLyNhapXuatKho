package com.example.bill_management.dto.requests;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class UserCreationRequest {
    private String username;
    private String password;
    private String name;

    @Builder.Default
    private boolean isDeleted = false;

}

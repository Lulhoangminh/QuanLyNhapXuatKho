package com.example.bill_management.dto.requests;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProductCreationRequest {
    private String id;

    private String name;

    private String unit;

    private Long unitPrice;

    @Builder.Default
    private LocalDate createdDate = LocalDate.now();

    @Builder.Default
    private boolean isDeleted = false;
}

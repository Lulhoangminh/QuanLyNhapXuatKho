package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProductRequest {
    private String id;

    private String name;

    private String unit;

    private Long unitPrice;

    @Builder.Default
    private LocalDate createdDate = LocalDate.now();

    @Builder.Default
    private boolean isDeleted = false;
}

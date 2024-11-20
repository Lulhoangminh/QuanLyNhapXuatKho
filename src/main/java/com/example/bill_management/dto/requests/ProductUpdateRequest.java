package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductUpdateRequest {
    private String id;

    private String name;

    private String unit;

    private Long unitPrice;
}

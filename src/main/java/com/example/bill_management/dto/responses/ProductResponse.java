package com.example.bill_management.dto.responses;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String unit;
    private Long unitPrice;

    // .... Other params
}

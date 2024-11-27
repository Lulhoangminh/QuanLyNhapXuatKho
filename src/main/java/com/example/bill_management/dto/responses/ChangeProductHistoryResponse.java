package com.example.bill_management.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class ChangeProductHistoryResponse {
    private Long id;
    private Long storageId;
    private String productId;
    private String type;
    private Long amount;
    private LocalDate createdDate;
}

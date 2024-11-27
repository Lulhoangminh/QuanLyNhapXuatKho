package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class ChangeProductHistoryRequest {
    private Long storageId;
    private String productId;
    private String type;
    private Long amount;

    @Builder.Default
    private LocalDate createdDate = LocalDate.now();

    public void addAmount(Long amount){
        this.amount = ((this.amount == null) ? 0 : this.amount) + amount;
    }
}

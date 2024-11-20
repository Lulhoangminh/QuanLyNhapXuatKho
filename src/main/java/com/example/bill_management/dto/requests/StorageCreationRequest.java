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
public class StorageCreationRequest {
    private String name;

    private String address;

    @Builder.Default
    private LocalDate createdDate = LocalDate.now();
}

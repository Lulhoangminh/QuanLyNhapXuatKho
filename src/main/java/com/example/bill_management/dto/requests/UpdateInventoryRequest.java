package com.example.bill_management.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateInventoryRequest {
    private Long inventory;
}

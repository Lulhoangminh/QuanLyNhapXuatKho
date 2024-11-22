package com.example.bill_management.dto.responses;

import lombok.*;
import org.springframework.stereotype.Repository;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StorageProductResponse {
    private String productId;
    private String productName;
    private String storageName;
    private Long inventory;
}

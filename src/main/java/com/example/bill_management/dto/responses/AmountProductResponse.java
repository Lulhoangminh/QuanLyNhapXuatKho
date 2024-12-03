package com.example.bill_management.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountProductResponse<T> {
    private String productId;
    private Long storageId;
    private String productName;
    private String storageName;
    private String amountName;
    private T amount;
}

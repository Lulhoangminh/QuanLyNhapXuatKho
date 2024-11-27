package com.example.bill_management.util;

import com.example.bill_management.dto.requests.ChangeProductHistoryRequest;
import com.example.bill_management.dto.responses.ChangeProductHistoryResponse;
import com.example.bill_management.entities.ChangeProductHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeProductHistoryUtil {
    public ChangeProductHistoryResponse changeProductHistoryResponse(ChangeProductHistoryEntity changeProductHistoryEntity){
        return ChangeProductHistoryResponse.builder()
                .id(changeProductHistoryEntity.getId())
                .storageId(changeProductHistoryEntity.getStorageId())
                .productId(changeProductHistoryEntity.getProductId())
                .type(changeProductHistoryEntity.getType())
                .createdDate(changeProductHistoryEntity.getCreatedDate())
                .amount(changeProductHistoryEntity.getAmount())
                .build();
    }
}

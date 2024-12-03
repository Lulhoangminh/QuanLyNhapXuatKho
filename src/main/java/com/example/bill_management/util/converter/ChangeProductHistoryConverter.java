package com.example.bill_management.util.converter;

import com.example.bill_management.dto.responses.ChangeProductHistoryResponse;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.entities.ChangeProductHistoryEntity;
import com.example.bill_management.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ChangeProductHistoryConverter implements EntityToResponseConverter <ChangeProductHistoryEntity, ChangeProductHistoryResponse>{
    @Override
    public ChangeProductHistoryResponse toResponseConverter(ChangeProductHistoryEntity changeProductHistoryEntity) {
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

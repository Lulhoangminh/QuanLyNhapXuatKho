package com.example.bill_management.util.converter;

import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.entities.StorageEntity;
import org.springframework.stereotype.Component;

@Component
public class StorageConverter implements EntityToResponseConverter<StorageEntity, StorageResponse>{
    @Override
    public StorageResponse toResponseConverter(StorageEntity storage) {
        return StorageResponse.builder()
                .name(storage.getName())
                .address(storage.getAddress())
                .build();
    }
}

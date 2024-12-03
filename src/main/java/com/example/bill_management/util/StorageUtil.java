package com.example.bill_management.util;

import com.example.bill_management.dto.requests.StorageRequest;
import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageUtil {
    @Autowired
    private StorageRepository storageRepository;

    public StorageResponse createStorage(StorageRequest request){
        StorageEntity storage = new StorageEntity();
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());
        storage.setCreatedDate(request.getCreatedDate());
        return toStorageResponse(storageRepository.save(storage));
    }

    public StorageResponse toStorageResponse(StorageEntity storage){
        return StorageResponse.builder()
                .name(storage.getName())
                .address(storage.getAddress())
                .build();
    }
}

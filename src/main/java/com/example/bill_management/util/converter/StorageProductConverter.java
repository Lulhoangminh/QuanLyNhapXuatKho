package com.example.bill_management.util.converter;

import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageProductConverter implements EntityToResponseConverter<StorageProductEntity, StorageProductResponse>{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public StorageProductResponse toResponseConverter(StorageProductEntity storageProductEntity) {
        return StorageProductResponse.builder()
                .productId(storageProductEntity.getProductId())
                .productName(productRepository.findNameById(storageProductEntity.getProductId()))
                .storageName(storageRepository.findNameById(storageProductEntity.getStorageId()))
                .inventory(storageProductEntity.getInventory())
                .build();
    }
}

package com.example.bill_management.util;

import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import org.springframework.stereotype.Component;

@Component
public class StorageProductUtil {

    private final StorageRepository storageRepository;
    private final ProductRepository productRepository;

    public StorageProductUtil(StorageRepository storageRepository,
                              ProductRepository productRepository) {
        this.storageRepository = storageRepository;
        this.productRepository = productRepository;
    }

    public StorageProductResponse toStorageProductResponse(StorageProductEntity storageProductEntity){
        return StorageProductResponse.builder()
                .productId(storageProductEntity.getProductId())
                .productName(productRepository.findNameById(storageProductEntity.getProductId()))
                .storageName(storageRepository.findNameById(storageProductEntity.getStorageId()))
                .inventory(storageProductEntity.getInventory())
                .build();
    }
}

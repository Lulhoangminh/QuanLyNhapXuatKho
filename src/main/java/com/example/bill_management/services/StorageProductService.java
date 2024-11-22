package com.example.bill_management.services;

import com.example.bill_management.dto.requests.AddProductToStorageRequest;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.util.StorageProductUtil;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageProductService {
    @Autowired
    private StorageProductRepository storageProductRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageProductUtil storageProductUtil;

    public StorageProductResponse addProductToStorage(Long storageId, AddProductToStorageRequest request){
        if (request.getQuantity() == null){
            throw new IllegalArgumentException("Quantity is not correct");
        }
        if(request.getQuantity() < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        StorageEntity storage = storageRepository.findById(storageId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
        ProductEntity product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
        StorageProductEntity storageProductEntity = storageProductRepository.findByProductIdAndStorageId(storageId, request.getProductId()).orElse(new StorageProductEntity());

        // check is create new ?
        if (storageProductEntity.getStorageId() == null || storageProductEntity.getProductId() == null){
            storageProductEntity.setProductId(request.getProductId());
            storageProductEntity.setStorageId(storageId);
        }
        storageProductEntity.addToInventory(request.getQuantity());
        return storageProductUtil.toStorageProductResponse(storageProductRepository.save(storageProductEntity));
    }

}

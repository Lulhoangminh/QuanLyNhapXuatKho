package com.example.bill_management.validator;

import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.StorageProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageValidator implements EntityValidator<Long, StorageEntity>{
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageProductRepository storageProductRepository;

    @Override
    public StorageEntity findById(Long entityId) {
        return storageRepository.findById(entityId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
    }

    public List<StorageEntity> findAll(){
        return storageRepository.findAll();
    }

    @Override
    public void validateExistenceById(Long entityId) {
//        if (storageRepository.existsById(entityId)){
//            throw new AppException(ECProductsStorage.EXISTENT_STORAGE_ID);
//        }
        return;
    }

    public void checkIfExistenceByName(String name){
        return;
    }

    public void checkInputQuantityOfProduct(Long quantity){
        if (quantity == null){
            throw new IllegalArgumentException("Quantity is not correct");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
    }

    public StorageProductEntity findOrCreateNewIfExistenceByStorageAndProductId(Long storageId, String productId){
        return storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElse(new StorageProductEntity());
    }

    public StorageProductEntity findByStorageAndProductId(Long storageId, String productId){
        return storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElseThrow(() -> new AppException(ECProductsStorage.PRODUCT_IS_NOT_IN_STORAGE));
    }

    public boolean isProductInStorage(Long storageId, String productId){
        return storageId == null || productId == null;
    }
}

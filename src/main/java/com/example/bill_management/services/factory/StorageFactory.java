package com.example.bill_management.services.factory;

import com.example.bill_management.dto.requests.StorageRequest;
import com.example.bill_management.dto.requests.UpdateAmountRequest;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.StorageProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.validator.ProductValidator;
import com.example.bill_management.validator.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StorageFactory implements  EntityFactory<Long, StorageEntity, StorageRequest> {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageValidator storageValidator;
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private StorageProductRepository storageProductRepository;

    @Override
    public StorageEntity createNewFromRequest(StorageRequest request) {
        StorageEntity storage = new StorageEntity();
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());
        storage.setCreatedDate(request.getCreatedDate());
        return storageRepository.save(storage);
    }

    @Override
    public StorageEntity updateById(Long id, StorageRequest request) {
        StorageEntity storage = storageValidator.findById(id);
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());

        return storageRepository.save(storage);
    }

    @Override
    public void deleteById(Long id) {
        StorageEntity storage = storageValidator.findById(id);
        storage.setDeleted(true);
        storage.setDeletedDate(LocalDate.now());
        storageRepository.save(storage);
    }

    public StorageProductEntity addQuantityToInventoryProduct(Long storageId, UpdateAmountRequest request){
        // check quantity to add is correct
        storageValidator.checkInputQuantityOfProduct(request.getQuantity());
        StorageProductEntity storageProductEntity = storageValidator.findOrCreateNewIfExistenceByStorageAndProductId(storageId, request.getProductId());

        // check is create new ?
        if (storageValidator.isProductInStorage(storageProductEntity.getStorageId(), storageProductEntity.getProductId())){
            storageProductEntity.setProductId(request.getProductId());
            storageProductEntity.setStorageId(storageId);
        }
        // add quantity to inventory
        storageProductEntity.addToInventory(request.getQuantity());
        return storageProductEntity;
    }

    public StorageProductEntity dispatchProductFromStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageValidator.findByStorageAndProductId(storageId, productId);
        storageProduct.dispatchProduct(request.getQuantity());
        return storageProduct;
    }

    public StorageProductEntity updateInventoryProductFromStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageValidator.findByStorageAndProductId(storageId, productId);
        storageProduct.setInventory(request.getQuantity());
        return storageProduct;
    }

    public String nameOfStorage(Long id){
        return storageValidator.findById(id).getName();
    }

}

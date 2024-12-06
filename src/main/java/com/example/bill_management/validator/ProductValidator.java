package com.example.bill_management.validator;

import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.services.factory.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductValidator implements EntityValidator<String, ProductEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity findById(String entityId) {
        return productRepository.findById(entityId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
    }

    @Override
    public void validateExistenceById(String entityId) {
        if(productRepository.existsById(entityId)){
            throw new AppException(ECProductsStorage.EXISTENT_PRODUCT_ID);
        }
    }

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }

    public void existsById(String entityId){
        if (!productRepository.existsById(entityId)){
            throw new AppException(ECProductsStorage.EXISTENT_PRODUCT_ID);
        }
    }

}

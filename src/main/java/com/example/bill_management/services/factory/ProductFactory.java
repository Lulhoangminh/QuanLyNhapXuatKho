package com.example.bill_management.services.factory;

import com.example.bill_management.dto.requests.ProductRequest;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ProductFactory implements EntityFactory <String, ProductEntity, ProductRequest>{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductValidator productValidator;

    @Override
    public ProductEntity createNewFromRequest(ProductRequest request){
        ProductEntity product = new ProductEntity();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setUnit(request.getUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setCreatedDate(LocalDate.now());
        product.setDeleted(request.isDeleted());

        return productRepository.save(product);
    }

    @Override
    public void deleteById(String id) {
        ProductEntity product = productValidator.findById(id);
        product.setDeleted(true);
        product.setDeletedDate(LocalDate.now());
        productRepository.save(product);
    }

    @Override
    public ProductEntity updateById(String id, ProductRequest request) {
        ProductEntity product = productValidator.findById(id);

        product.setId(request.getId());
        product.setUnitPrice(request.getUnitPrice());
        product.setName(request.getName());
        product.setUnit(request.getUnit());

        return productRepository.save(product);
    }
}

package com.example.bill_management.services;

import com.example.bill_management.dto.requests.ProductRequest;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.services.factory.ProductFactory;
import com.example.bill_management.util.ProductUtil;
import com.example.bill_management.util.converter.ProductConverter;
import com.example.bill_management.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductUtil productUtil;
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private ProductConverter productConverter;

    public ProductResponse createProduct(ProductRequest request){
        // check existence
        productValidator.validateExistenceById(request.getId());

        // create if new
        ProductEntity product = productFactory.createNewFromRequest(request);

        return productConverter.toResponseConverter(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts(){
        return productValidator.findAll().stream()
                .map(productConverter::toResponseConverter)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(String id){
        ProductEntity product = productValidator.findById(id);
        return productConverter.toResponseConverter(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest request){
        ProductEntity product = productFactory.updateById(id, request);
        return productConverter.toResponseConverter(product);
    }

    public Void deleteProduct(String id){
        productFactory.deleteById(id);
        return null;
    }
}

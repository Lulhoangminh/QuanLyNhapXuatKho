package com.example.bill_management.services;

import com.example.bill_management.dto.requests.ProductCreationRequest;
import com.example.bill_management.dto.requests.ProductUpdateRequest;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.util.ProductUtil;
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

    public ProductResponse createProduct(ProductCreationRequest request){
        if (productRepository.existsById(request.getId())){
            throw new AppException(ECProductsStorage.EXISTENT_PRODUCT_ID);
        }
        ProductEntity product = new ProductEntity();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setUnit(request.getUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setCreatedDate(LocalDate.now());
        product.setDeleted(request.isDeleted());

        return productUtil.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts(){
        List<ProductEntity> listProducts = productRepository.findAll();
        return listProducts.stream()
                .map(product -> productUtil.toProductResponse(product))
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(String id){
        ProductEntity product = productRepository.findById(id).orElseThrow(()-> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
        return productUtil.toProductResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductUpdateRequest request){
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));

        product.setId(request.getId());
        product.setUnitPrice(request.getUnitPrice());
        product.setName(request.getName());
        product.setUnit(request.getUnit());

        return productUtil.toProductResponse(product);
    }

    public Void deleteProduct(String id){
        ProductEntity product = productRepository.findById(id).orElseThrow(() ->new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
        product.setDeleted(true);
        product.setDeletedDate(LocalDate.now());
        productRepository.save(product);
        return null;
    }
}

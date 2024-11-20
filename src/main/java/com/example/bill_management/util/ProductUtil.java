package com.example.bill_management.util;

import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductUtil {
    public ProductResponse toProductResponse(ProductEntity product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .unit(product.getUnit())
                .unitPrice(product.getUnitPrice())
                .build();
    }
}

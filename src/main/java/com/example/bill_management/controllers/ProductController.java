package com.example.bill_management.controllers;

import com.example.bill_management.dto.requests.ProductCreationRequest;
import com.example.bill_management.dto.requests.ProductUpdateRequest;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts(){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProduct(@PathVariable("id") String id){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable("id") String id, @RequestBody ProductUpdateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProduct(@PathVariable("id") String id){
        return ApiResponse.<Void>builder()
                .result(productService.deleteProduct(id))
                .message("Product with id " + id + " is deleted")
                .build();
    }
}

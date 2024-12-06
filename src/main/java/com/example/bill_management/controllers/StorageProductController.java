package com.example.bill_management.controllers;

import com.example.bill_management.dto.requests.AddProductToStorageRequest;
import com.example.bill_management.dto.requests.UpdateAmountRequest;
import com.example.bill_management.dto.requests.UpdateInventoryRequest;
import com.example.bill_management.dto.responses.AmountProductResponse;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.ProductResponse;
import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.services.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StorageProductController {
    @Autowired
    private StorageProductService storageProductService;

    // add
    @PostMapping("/storages/{storageId}/products")
    ApiResponse<StorageProductResponse> addProductToStorage(@PathVariable("storageId") Long storageId, @RequestBody UpdateAmountRequest request){
        return ApiResponse.<StorageProductResponse>builder()
                .result(storageProductService.addProductToStorage(storageId, request))
                .build();
    }

    @DeleteMapping("/storages/{storageId}/products/{productId}")
    ApiResponse<Void> deleteProductFromStorage(@PathVariable("storageId") Long storageId, @PathVariable("productId") String productId){
        return ApiResponse.<Void>builder()
                .result(storageProductService.deleteProductFromStorage(storageId, productId))
                .build();
    }

//    // Add quantity to inventory
//    @PostMapping("/storages/{storageId}/products/{productId}/add")
//    ApiResponse<StorageProductResponse> addInventoryProductToStorage(@PathVariable("storageId") Long storageId, @PathVariable("productId") String productId, @RequestBody UpdateAmountRequest request){
//        return ApiResponse.<StorageProductResponse> builder()
//                .result(storageProductService.addInventoryProductToStorage(storageId, productId, request))
//                .build();
//    }

    // Dispatch goods
    @PutMapping("/storages/{storageId}/products/{productId}/dispatch")
    ApiResponse<StorageProductResponse> dispatchProductsFromStorage(@PathVariable("storageId") Long storageId, @PathVariable("productId") String productId, @RequestBody UpdateAmountRequest request){
        return ApiResponse.<StorageProductResponse> builder()
                .result(storageProductService.dispatchProductFromStorage(storageId, productId, request))
                .build();
    }

    // Update the inventory of the product in 1 storage
    @PutMapping("/storages/{storageId}/products/{productId}")
    ApiResponse<StorageProductResponse> updateInventoryInStorage(@PathVariable("storageId") Long storageId, @PathVariable("productId") String productId, @RequestBody UpdateAmountRequest request){
        return ApiResponse.<StorageProductResponse>builder()
                .result(storageProductService.updateInventoryInStorage(storageId, productId, request))
                .build();
    }

    // Get all products in 1 storage
    @GetMapping("/storages/{storageId}/products")
    ApiResponse<List<StorageProductResponse>> getAllProductsInStorage(@PathVariable("storageId") Long storageId){
        return ApiResponse.<List<StorageProductResponse>> builder()
                .result(storageProductService.getAllProductsInStorage(storageId))
                .build();
    }

    // Get all storages that have Product
    @GetMapping("/products/{productId}/storages")
    ApiResponse<List<StorageProductResponse>> getAllStoragesHaveProduct(@PathVariable("productId")String productId){
        return ApiResponse.<List<StorageProductResponse>> builder()
                .result(storageProductService.getAllStoragesHaveProduct(productId))
                .build();
    }

    // Get inventory of product
    @GetMapping(("/storages/{storageId}/products/{productId}/inventory"))
    ApiResponse<AmountProductResponse<?>> getInventory(@PathVariable("storageId") Long storageId, @PathVariable("productId") String productId){
        return ApiResponse.<AmountProductResponse<?>>builder()
                .result(storageProductService.getInventory(storageId, productId))
                .build();
    }
}

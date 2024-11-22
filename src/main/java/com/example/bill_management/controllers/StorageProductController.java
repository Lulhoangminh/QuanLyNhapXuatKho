package com.example.bill_management.controllers;

import com.example.bill_management.dto.requests.AddProductToStorageRequest;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.services.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StorageProductController {
    @Autowired
    private StorageProductService storageProductService;

    @PostMapping("/storages/{storageId}/products")
    ApiResponse<StorageProductResponse> addProductToStorage(@PathVariable("storageId") Long storageId, @RequestBody AddProductToStorageRequest request){
        return ApiResponse.<StorageProductResponse>builder()
                .result(storageProductService.addProductToStorage(storageId, request))
                .build();
    }
}

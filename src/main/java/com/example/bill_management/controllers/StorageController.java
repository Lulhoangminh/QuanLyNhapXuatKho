package com.example.bill_management.controllers;

import com.example.bill_management.dto.requests.StorageCreationRequest;
import com.example.bill_management.dto.requests.StorageUpdateRequest;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storages")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping
    ApiResponse<StorageResponse> createStorage(@RequestBody StorageCreationRequest request){
        return ApiResponse.<StorageResponse>builder()
                .result(storageService.createStorage(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<StorageResponse>> getAllStorages(){
        return ApiResponse.<List<StorageResponse>>builder()
                .result(storageService.getAllStorages())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<StorageResponse> getStorage(@PathVariable("id") Long id){
        return ApiResponse.<StorageResponse>builder()
                .result(storageService.getStorage(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<StorageResponse> updateStorage(@PathVariable("id") Long id, @RequestBody StorageUpdateRequest request){
        return ApiResponse.<StorageResponse>builder()
                .result(storageService.updateStorage(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteStorage(@PathVariable("id") Long id){
        return ApiResponse.<Void>builder()
                .result(storageService.deleteStorage(id))
                .build();
    }
}

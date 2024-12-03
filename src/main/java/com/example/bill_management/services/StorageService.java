package com.example.bill_management.services;


import com.example.bill_management.dto.requests.StorageRequest;
import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageUtil storageUtil;
    public StorageResponse createStorage(StorageRequest request){
        return storageUtil.createStorage(request);
    }

    public List<StorageResponse> getAllStorages(){
        return storageRepository.findAll().stream()
                .map(storage -> storageUtil.toStorageResponse(storage))
                .collect(Collectors.toList());
    }

    public StorageResponse getStorage(Long id) {
        return storageUtil.toStorageResponse(
                storageRepository.findById(id)
                        .orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID))
        );
    }

    public StorageResponse updateStorage(Long id, StorageRequest request){
        StorageEntity storage = storageRepository.findById(id).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());

        return storageUtil.toStorageResponse(storageRepository.save(storage));
    }

    public Void deleteStorage(Long id){
        StorageEntity storage = storageRepository.findById(id).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
        storage.setDeleted(true);
        storage.setDeletedDate(LocalDate.now());
        storageRepository.save(storage);
        return null;
    }
}

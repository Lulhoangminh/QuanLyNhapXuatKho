package com.example.bill_management.services;


import com.example.bill_management.dto.requests.StorageRequest;
import com.example.bill_management.dto.responses.StorageResponse;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.services.factory.StorageFactory;
import com.example.bill_management.util.converter.StorageConverter;
import com.example.bill_management.validator.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {
    @Autowired
    private StorageFactory storageFactory;
    @Autowired
    private StorageValidator storageValidator;
    @Autowired
    private StorageConverter storageConverter;

    public StorageResponse createStorage(StorageRequest request){
        StorageEntity storage = storageFactory.createNewFromRequest(request);
        return storageConverter.toResponseConverter(storage);
    }

    public List<StorageResponse> getAllStorages(){
        return storageValidator.findAll().stream()
                .map(storageConverter::toResponseConverter)
                .collect(Collectors.toList());
    }

    public StorageResponse getStorage(Long id) {
        StorageEntity storage = storageValidator.findById(id);
        return storageConverter.toResponseConverter(storage);
    }

    public StorageResponse updateStorage(Long id, StorageRequest request){
        StorageEntity storage = storageFactory.updateById(id, request);
        return storageConverter.toResponseConverter(storage);
    }

    public Void deleteStorage(Long id){
        storageFactory.deleteById(id);
        return null;
    }
}

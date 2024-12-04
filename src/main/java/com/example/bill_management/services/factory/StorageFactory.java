package com.example.bill_management.services.factory;

import com.example.bill_management.dto.requests.StorageRequest;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.validator.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StorageFactory implements  EntityFactory<Long, StorageEntity, StorageRequest> {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageValidator storageValidator;

    @Override
    public StorageEntity createNewFromRequest(StorageRequest request) {
        StorageEntity storage = new StorageEntity();
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());
        storage.setCreatedDate(request.getCreatedDate());
        return storageRepository.save(storage);
    }

    @Override
    public StorageEntity updateById(Long id, StorageRequest request) {
        StorageEntity storage = storageValidator.findById(id);
        storage.setName(request.getName());
        storage.setAddress(request.getAddress());

        return storageRepository.save(storage);
    }

    @Override
    public void deleteById(Long id) {
        StorageEntity storage = storageValidator.findById(id);
        storage.setDeleted(true);
        storage.setDeletedDate(LocalDate.now());
        storageRepository.save(storage);
    }
}

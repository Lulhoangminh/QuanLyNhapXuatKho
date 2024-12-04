package com.example.bill_management.validator;

import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageValidator implements EntityValidator<Long, StorageEntity>{
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public StorageEntity findById(Long entityId) {
        return storageRepository.findById(entityId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
    }

    public List<StorageEntity> findAll(){
        return storageRepository.findAll();
    }

    @Override
    public void validateExistenceById(Long entityId) {
        return;
    }

    public void checkIfExistenceByName(String name){
        return;
    }
}

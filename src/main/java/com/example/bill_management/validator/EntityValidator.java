package com.example.bill_management.validator;

import org.springframework.stereotype.Component;

@Component
public interface EntityValidator <ID, ENTITY>{
//    void validatorExistence(T entityId);
    void validateExistenceById(ID entityId);
    ENTITY findById(ID entityId);
}

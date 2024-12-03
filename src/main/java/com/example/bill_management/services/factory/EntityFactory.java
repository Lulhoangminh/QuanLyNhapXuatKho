package com.example.bill_management.services.factory;

import org.springframework.stereotype.Component;

@Component
public interface EntityFactory <IDTYPE, ENTITY, REQUEST>{
    ENTITY createNewFromRequest(REQUEST request);
    ENTITY updateById(IDTYPE id, REQUEST request);
    void deleteById(IDTYPE id);
}

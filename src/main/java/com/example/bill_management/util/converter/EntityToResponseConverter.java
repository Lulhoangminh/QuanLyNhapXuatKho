package com.example.bill_management.util.converter;

import org.springframework.stereotype.Component;

@Component
public interface EntityToResponseConverter <ENTITY, RESPONSE> {
    RESPONSE toResponseConverter(ENTITY entity);
}

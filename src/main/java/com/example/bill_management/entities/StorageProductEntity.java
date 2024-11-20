package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StorageProductEntity extends BaseIdEntity{
    private String product_id;
    private String storage_id;
    private Long inventory;
}

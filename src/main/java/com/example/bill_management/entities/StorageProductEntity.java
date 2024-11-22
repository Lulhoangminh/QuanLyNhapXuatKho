package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StorageProductEntity extends BaseIdEntity{
    private String productId;
    private Long storageId;
    private Long inventory;

    public void addToInventory(Long quantity){
        this.inventory = ((this.inventory == null) ? 0 : this.inventory) + quantity;
    }
}

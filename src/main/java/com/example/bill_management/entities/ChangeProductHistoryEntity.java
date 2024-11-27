package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ChangeProductHistoryEntity extends BaseIdEntity{
    private Long storageId;
    private String productId;
    private String type;
    private Long amount;
    private LocalDate createdDate;

    public void addAmount(Long amount){
        this.amount = ((this.amount == null) ? 0 : this.amount) + amount;
    }
}

package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductEntity {
    @Id
    private String id;

    private String name;

    private String unit;

    private Long unitPrice;

    private LocalDate createdDate;

    private boolean isDeleted;

    private LocalDate deletedDate;
}

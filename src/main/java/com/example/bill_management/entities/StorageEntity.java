package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class StorageEntity extends BaseIdEntity {
    private String address;
    private String name;
    private LocalDate createdDate;
    private boolean isDeleted;
    private LocalDate deletedDate;
}

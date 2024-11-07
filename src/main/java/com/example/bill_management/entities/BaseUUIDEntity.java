package com.example.bill_management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseUUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}

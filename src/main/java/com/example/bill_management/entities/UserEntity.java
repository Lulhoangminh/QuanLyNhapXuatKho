package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseUUIDEntity{
    private String name;
    private String username;
    private String password;
}

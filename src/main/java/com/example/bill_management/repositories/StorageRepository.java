package com.example.bill_management.repositories;


import com.example.bill_management.entities.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {
}

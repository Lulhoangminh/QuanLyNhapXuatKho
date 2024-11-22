package com.example.bill_management.repositories;


import com.example.bill_management.entities.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {
    @Query("SELECT s.name FROM StorageEntity s WHERE s.id = :id")
    String findNameById(@Param("id") Long id);
}

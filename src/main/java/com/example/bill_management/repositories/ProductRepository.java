package com.example.bill_management.repositories;

import com.example.bill_management.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("SELECT s.name FROM ProductEntity s WHERE s.id = :id")
    String findNameById(@Param("id") String id);
}

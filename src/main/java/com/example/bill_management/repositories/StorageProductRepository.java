package com.example.bill_management.repositories;


import com.example.bill_management.entities.StorageProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageProductRepository extends JpaRepository<StorageProductEntity, Long> {
    @Query("""
                SELECT CASE WHEN COUNT(sp) > 0 THEN true ELSE false END
                FROM StorageProductEntity sp
                WHERE sp.productId = :productId AND sp.storageId = :storageId
            """)
    boolean existsByStorageAndProductId(
            @Param("storageId") Long storageId,
            @Param("productId") String productId
    );

    @Query("SELECT sp FROM StorageProductEntity sp WHERE sp.productId = :productId AND sp.storageId = :storageId")
    Optional<StorageProductEntity> findByProductIdAndStorageId(@Param("storageId") Long storageId,
                                                                @Param("productId") String productId);

    @Query("SELECT sp FROM StorageProductEntity sp WHERE sp.storageId = :storageId")
    List<StorageProductEntity> findAllByStorageId(@Param("storageId") Long storageId);

    @Query("SELECT sp FROM StorageProductEntity sp WHERE sp.productId = :productId")
    List<StorageProductEntity> findAllByProductId(@Param("productId") String productId);

}

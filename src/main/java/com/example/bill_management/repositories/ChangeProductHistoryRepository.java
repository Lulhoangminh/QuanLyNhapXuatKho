package com.example.bill_management.repositories;

import com.example.bill_management.entities.ChangeProductHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ChangeProductHistoryRepository extends JpaRepository<ChangeProductHistoryEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM ChangeProductHistoryEntity c " +
            "WHERE c.storageId = :storageId " +
            "AND c.productId = :productId " +
            "AND c.type = :type " +
            "AND c.createdDate = :today")
    boolean existsChangeInQuantityToday(@Param("storageId") Long storageId,
                                        @Param("productId") String productId,
                                        @Param("type") String type,
                                        @Param("today") LocalDate today);

    @Query("SELECT c " +
            "FROM ChangeProductHistoryEntity c " +
            "WHERE c.storageId = :storageId " +
            "AND c.productId = :productId " +
            "AND c.type = :type " +
            "AND c.createdDate = :today")
    Optional<ChangeProductHistoryEntity> findChangeInQuantityToday(@Param("storageId") Long storageId,
                                       @Param("productId") String productId,
                                       @Param("type") String type,
                                       @Param("today") LocalDate today);
}

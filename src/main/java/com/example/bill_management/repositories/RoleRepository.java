package com.example.bill_management.repositories;

import com.example.bill_management.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r FROM RoleEntity r WHERE r.name = :name")
    Optional<RoleEntity> findByName(@Param("name") String name);

    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END\s
            FROM RoleEntity r\s
            WHERE r.name = :name\s""")
    boolean existsByName(@Param("name") String name);

}

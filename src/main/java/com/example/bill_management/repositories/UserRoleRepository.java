package com.example.bill_management.repositories;

import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    @Query("""
            SELECT ur
            FROM UserRoleEntity ur
            WHERE ur.roleId = :roleId
            """)
    Optional<UserRepository> findByRoleId(@Param("roleId") Long roleId);

    @Query("""
            SELECT CASE WHEN COUNT(ur) > 0 THEN true ELSE false END
            FROM UserRoleEntity ur
            WHERE ur.roleId = :roleId
            """)
    boolean existsByRoleId(@Param("roleId") Long roleId);

    @Query("""
            SELECT r.name
            FROM RoleEntity r JOIN UserRoleEntity ur ON r.id = ur.roleId
            WHERE ur.userId = :userId
            """)
    List<String> findRoleNamesByUserId(@Param("userId") String userId);

    @Query("""
            SELECT r.name
            FROM RoleEntity r JOIN UserRoleEntity ur ON r.id = ur.roleId
            WHERE ur.userId = :userId
            """)
    List<RoleEntity> findRoleByUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserRoleEntity ur WHERE ur.userId = :userId")
    void deleteByUserId(@Param("userId")String userId);

    @Query("""
            SELECT CASE
                WHEN r.name = "ADMIN" THEN true
                    ELSE false
                END
            FROM RoleEntity r JOIN UserRoleEntity ur on r.id = ur.roleId
            WHERE ur.userId = :userId
            """)
    boolean isAdminTargeted (@Param("userId") String userId);
}

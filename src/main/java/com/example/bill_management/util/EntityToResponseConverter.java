package com.example.bill_management.util;

import com.example.bill_management.dto.responses.*;
import com.example.bill_management.entities.*;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.RoleRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityToResponseConverter {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;

    public UserResponse toUserResponseFromUserEntity(UserEntity user){
        String userId = user.getId();

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(userRoleRepository.findRoleNamesByUserId(userId))
                .build();
    }

    public ProductResponse toProductResponse(ProductEntity product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .unit(product.getUnit())
                .unitPrice(product.getUnitPrice())
                .build();
    }

    public StorageResponse toStorageResponse(StorageEntity storage){
        return StorageResponse.builder()
                .name(storage.getName())
                .address(storage.getAddress())
                .build();
    }

    public StorageProductResponse toStorageProductResponse(StorageProductEntity storageProductEntity){
        return StorageProductResponse.builder()
                .productId(storageProductEntity.getProductId())
                .productName(productRepository.findNameById(storageProductEntity.getProductId()))
                .storageName(storageRepository.findNameById(storageProductEntity.getStorageId()))
                .inventory(storageProductEntity.getInventory())
                .build();
    }

    public ChangeProductHistoryResponse changeProductHistoryResponse(ChangeProductHistoryEntity changeProductHistoryEntity){
        return ChangeProductHistoryResponse.builder()
                .id(changeProductHistoryEntity.getId())
                .storageId(changeProductHistoryEntity.getStorageId())
                .productId(changeProductHistoryEntity.getProductId())
                .type(changeProductHistoryEntity.getType())
                .createdDate(changeProductHistoryEntity.getCreatedDate())
                .amount(changeProductHistoryEntity.getAmount())
                .build();
    }

}

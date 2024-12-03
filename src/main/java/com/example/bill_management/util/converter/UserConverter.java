package com.example.bill_management.util.converter;

import com.example.bill_management.dto.responses.UserResponse;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverter implements EntityToResponseConverter<UserEntity, UserResponse> {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserResponse toResponseConverter(UserEntity user) {
        String userId = user.getId();

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(userRoleRepository.findRoleNamesByUserId(userId))
                .build();
    }
}

package com.example.bill_management.validator;

import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECRole;
import com.example.bill_management.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class RoleValidator implements EntityValidator<Long>{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void validateExistenceById(Long entityId) {
        return;
    }

    public RoleEntity findRoleByName(String roleName){
        return roleRepository.findByName(roleName).orElseThrow(() -> new AppException(ECRole.NONEXISTENT_ROLE));
    }

}

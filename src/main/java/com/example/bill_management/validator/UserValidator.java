package com.example.bill_management.validator;

import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.entities.UserRoleEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECUser;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidator implements EntityValidator<String, UserEntity> {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;

    @Override
    public void validateExistenceById(String entityId) {
        if(userRepository.existsById(entityId)){
            throw new AppException(ECUser.EXISTENT_USER);
        }
    }

    @Override
    public UserEntity findById(String entityId) {
        return userRepository.findById(entityId).orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));
    }

    public void validateExistenceByUsername(String username){
        if (userRepository.existsByUsername(username)){
            throw new AppException(ECUser.EXISTENT_USER);
        }
    }

    public UserEntity findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));
    }

    public void checkIfTargetIsAdmin(String id){
        if(userRoleRepository.isAdminTargeted(id)){
            throw new AppException(ECUser.WARNING_ADMIN_ID_IS_DELETED);
        }
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }
}

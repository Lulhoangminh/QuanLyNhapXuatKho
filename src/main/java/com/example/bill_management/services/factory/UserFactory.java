package com.example.bill_management.services.factory;

import com.example.bill_management.dto.requests.UserRequest;
import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.entities.UserRoleEntity;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import com.example.bill_management.validator.RoleValidator;
import com.example.bill_management.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class UserFactory implements EntityFactory <String, UserEntity, UserRequest>{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleValidator roleValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserEntity createNewFromRequest(UserRequest request){
        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDeleted(request.isDeleted());

        return userRepository.save(user);
    }

    public UserRoleEntity createUserRole(String userId, String roleName){
        RoleEntity role = roleValidator.findRoleByName(roleName);
        return userRoleRepository.save(new UserRoleEntity(userId, role.getId()));
    }

    @Override
    public UserEntity updateById(String id, UserRequest request){
        UserEntity user = userValidator.findById(id);
        user.setName(request.getName());
        user.setUsername(request.getUsername());

        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id){
        // check is target for deleting is "admin" ?
        userValidator.checkIfTargetIsAdmin(id);

        UserEntity user = userValidator.findById(id);
        user.setDeleted(true);
        user.setDeleteDate(LocalDate.now());

        userRepository.save(user);
    }
}

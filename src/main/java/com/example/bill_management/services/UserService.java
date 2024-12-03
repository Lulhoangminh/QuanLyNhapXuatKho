package com.example.bill_management.services;

import com.example.bill_management.dto.requests.UserRequest;
import com.example.bill_management.dto.responses.UserResponse;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.entities.UserRoleEntity;
import com.example.bill_management.enums.RoleEnum;
import com.example.bill_management.services.factory.UserFactory;
import com.example.bill_management.util.converter.UserConverter;
import com.example.bill_management.validator.RoleValidator;
import com.example.bill_management.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private RoleValidator roleValidator;

    @Transactional
    public UserResponse createUser(UserRequest request){
//        return userUtil.createUserWithRole(request, "USER");
        // check username is existed
        userValidator.validateExistenceByUsername(request.getUsername());

        // create user info
        UserEntity user = userFactory.createNewFromRequest(request);

        // assign new user with role "User"
        UserRoleEntity userRoleEntity = userFactory.createUserRole(user.getId(), RoleEnum.USER.name());

        // return info
        return userConverter.toResponseConverter(user);
    }

    public List<UserResponse> getAllUsers() {
        return userValidator.findAll().stream()
                .map(userConverter::toResponseConverter)
                .collect(Collectors.toList());
    }


    public UserResponse getUser(String id){
        UserEntity user = userValidator.findById(id);
        return userConverter.toResponseConverter(user);
    }

    @Transactional
    public UserResponse updateUser(String id, UserRequest request){
        UserEntity user = userFactory.updateById(id, request);
        return userConverter.toResponseConverter(user);
    }

    @Transactional
    public Void deleteUser(String id){
        userFactory.deleteById(id);
        return null;
    }
}

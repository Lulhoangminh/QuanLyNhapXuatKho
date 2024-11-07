package com.example.bill_management.services;

import com.example.bill_management.dto.requests.UserCreationRequest;
import com.example.bill_management.dto.requests.UserUpdateRequest;
import com.example.bill_management.dto.responses.ApiResponse;
import com.example.bill_management.dto.responses.UserResponse;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECUser;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import com.example.bill_management.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserUtil userUtil;

    public UserResponse createUser(UserCreationRequest request){
        return userUtil.createUserWithRole(request, "USER");
    }

    public List<UserResponse> getAllUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (UserEntity user : userEntityList){
            userResponseList.add(userUtil.toUserResponseFromUserEntity(user));
        }
        return userResponseList;
    }

    public UserResponse getUser(String id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));

        new UserResponse();
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        userRepository.save(user);

        new UserResponse();
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    public Void deleteUser(String id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AppException(ECUser.NONEXISTENT_USER));
        userRoleRepository.deleteByUserId(user.getId());
        userRepository.deleteById(user.getId());
        return null;
    }
}

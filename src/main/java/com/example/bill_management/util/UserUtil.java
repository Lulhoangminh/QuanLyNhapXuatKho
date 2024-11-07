package com.example.bill_management.util;

import com.example.bill_management.dto.requests.UserCreationRequest;
import com.example.bill_management.dto.responses.UserResponse;
import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.entities.UserRoleEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECRole;
import com.example.bill_management.exceptions.ECUser;
import com.example.bill_management.repositories.RoleRepository;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtil {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUserWithRole(UserCreationRequest request, String roleName){
        // check username is existed
        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ECUser.EXISTENT_USER);
        }

        // create user info
        UserEntity user = new UserEntity(
                request.getName(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
        // saving user info
        userRepository.save(user);

        RoleEntity role = roleRepository.findByName(roleName).orElseThrow(() -> new AppException(ECRole.NONEXISTENT_ROLE));
        UserRoleEntity userRoleEntity = new UserRoleEntity(user.getId(), role.getId());
        userRoleRepository.save(userRoleEntity);

        List<String> listRoleNames = userRoleRepository.findRoleNamesByUserId(user.getId());
        // return info
        new UserResponse();
        return UserResponse.builder().name(user.getName()).username(user.getUsername()).roles(listRoleNames).build();
    }

    public UserResponse toUserResponseFromUserEntity(UserEntity user){
        String userId = user.getId();

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(userRoleRepository.findRoleNamesByUserId(userId))
                .build();
    }
}

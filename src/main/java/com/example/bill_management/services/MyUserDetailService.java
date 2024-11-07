package com.example.bill_management.services;

import com.example.bill_management.entities.MyUserDetail;
import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECUser;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new AppException(ECUser.NONEXISTENT_USER));
        List<RoleEntity> listRoles = userRoleRepository.findRoleByUserId(user.getId());
        return new MyUserDetail(user, listRoles);
    }
}

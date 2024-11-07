package com.example.bill_management.entities;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetail implements UserDetails {
    private final UserEntity user;
    private final List<RoleEntity> listRoles;

    public MyUserDetail(UserEntity user, List<RoleEntity> listRoles){
        this.user = user;
        this.listRoles = listRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        for(RoleEntity role : listRoles) {
//            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        }
//        return grantedAuthorityList;
        return listRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}

package com.example.bill_management.config;

import com.example.bill_management.entities.Role;
import com.example.bill_management.enums.RoleEnum;
import com.example.bill_management.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationInitialConfiguration {
    @Autowired
    private RoleRepository roleRepository;

    // @PostConstruct: Annotation này đánh dấu phương thức initialize() để nó được gọi tự động ngay sau khi
    // Spring hoàn tất quá trình khởi tạo các thành phần trong ngữ cảnh ứng dụng.
    // Khi đó, các repository (roleRepository, userRepository) đã sẵn sàng để sử dụng.
    @PostConstruct
    public void initial(){
        roleInitial();
    }

    /*
    * Create "role" is defined in "RoleEnum"
    */
    private void roleInitial(){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if (!roleRepository.existsByName(roleEnum.name())){
                new Role();
                roleRepository.save(
                        Role.builder()
                        .name(roleEnum.name())
                        .build()
                );
            }
        }
    }

    private void userManagementInitial(){

    }
}

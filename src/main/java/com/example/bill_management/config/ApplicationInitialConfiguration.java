package com.example.bill_management.config;

import com.example.bill_management.dto.requests.UserCreationRequest;
import com.example.bill_management.entities.RoleEntity;
import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.enums.RoleEnum;
import com.example.bill_management.repositories.RoleRepository;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.util.UserUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ApplicationInitialConfiguration {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserUtil userUtil;

    // @PostConstruct: Annotation này đánh dấu phương thức initialize() để nó được gọi tự động ngay sau khi
    // Spring hoàn tất quá trình khởi tạo các thành phần trong ngữ cảnh ứng dụng.
    // Khi đó, các repository (roleRepository, userRepository) đã sẵn sàng để sử dụng.
    @PostConstruct
    public void initial(){
        roleInitial();
        adminInitial();
    }

    /*
    * Create "role" is defined in "RoleEnum"
    */
    private void roleInitial(){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if (!roleRepository.existsByName(roleEnum.name())){
                new RoleEntity();
                roleRepository.save(
                        RoleEntity.builder()
                        .name(roleEnum.name())
                        .build()
                );
            }
        }
    }

    private void adminInitial(){
        if (userRepository.existsByUsername("admin")){
            return;
        }
        new UserCreationRequest();
        UserCreationRequest adminCreationRequest = UserCreationRequest.builder()
                .username("admin")
                .password("admin")
                .isDeleted(false)
                .name("ADMIN")
                .build();
        userUtil.createUserWithRole(adminCreationRequest, "ADMIN");
        return;
    }
}

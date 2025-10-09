package com.quynhtien.cineasy.configuration;

import com.quynhtien.cineasy.entity.Role;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.enums.RoleEnum;
import com.quynhtien.cineasy.repository.RoleRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Configuration
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {
                Role role = roleRepository.save(Role.builder().name(RoleEnum.ADMIN.name()).build());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
                        .firstName("admin")
                        .lastName("admin")
                        .email("admin@admin.com")
                        .roles(List.of(role))
                        .build();
                userRepository.save(user);
                log.info("Created admin user with username 'admin' with authority ADMIN");
            }
        };
    }
}

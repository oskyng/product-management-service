package com.example.productmanagement;

import com.example.productmanagement.model.Role;
import com.example.productmanagement.model.User;
import com.example.productmanagement.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(Role.USER));
                userRepository.save(user);
                System.out.println("Usuario 'user' creado.");
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                admin.setRoles(Set.of(Role.ADMIN, Role.USER));
                userRepository.save(admin);
                System.out.println("Usuario 'admin' creado.");
            }
        };
    }

}

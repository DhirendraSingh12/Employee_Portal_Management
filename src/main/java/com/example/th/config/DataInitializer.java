package com.example.th.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.th.model.User;
import com.example.th.repository.UserRepository;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default Super Admin
        createDefaultUser("superadmin", "Hfx@123hFx", Set.of("ROLE_SUPERADMIN"));

        // Create default Admin
        createDefaultUser("admin", "Hflex@123Flex", Set.of("ROLE_ADMIN"));
    }

    private void createDefaultUser(String username, String password, Set<String> roles) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}

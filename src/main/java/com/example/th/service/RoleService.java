package com.example.th.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.th.model.Role;
import com.example.th.model.User;
import com.example.th.repository.RoleRepository;
import com.example.th.repository.UserRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Assign a role to a user
    public void assignRoleToUser(String userId, String roleName) {
        Optional<User> userOptional = userRepository.findById(userId);
        Role role = roleRepository.findByName(roleName);

        if (userOptional.isPresent() && role != null) {
            User user = userOptional.get();
          //  user.getRoles().add(role); // Assuming `roles` is a collection of Role objects
            userRepository.save(user);
        } else {
            // Handle error: either user or role was not found
            throw new RuntimeException("User or Role not found");
        }
    }
}
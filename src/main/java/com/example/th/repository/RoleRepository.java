package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.th.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
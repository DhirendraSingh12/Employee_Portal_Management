package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom query methods can be added here if needed
}
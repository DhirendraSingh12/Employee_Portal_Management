package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.Documents;


public interface DocumentRepository extends MongoRepository<Documents, String> {
}
package com.example.th.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.TimeOffRequest;

public interface TimeOffRequestRepository extends MongoRepository<TimeOffRequest, String> {
    List<TimeOffRequest> findByStatus(String status);

}

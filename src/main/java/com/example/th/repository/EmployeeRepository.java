package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
	 boolean existsByEmail(String email);
}
package com.example.th.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.th.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
	 @Query(value = "{'employeeId': {$regex: ?0}}", sort = "{'employeeId': -1}")
    Employee findTopByEmployeeIdStartsWithOrderByEmployeeIdDesc(String prefix);

    default int findMaxEmployeeIdNumber(String prefix) {
        Employee topEmployee = findTopByEmployeeIdStartsWithOrderByEmployeeIdDesc(prefix);
        if (topEmployee == null) {
            return 0;
        }
        String numericPart = topEmployee.getEmployeeId().substring(prefix.length());
        return Integer.parseInt(numericPart);
    }
    
	 boolean existsByEmail(String email);

	;
}
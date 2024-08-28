package com.example.th.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.Timesheet;

public interface TimesheetRepository extends MongoRepository<Timesheet, String> {
	List<Timesheet> findPendingTimesheetsByEmployeeId(String employeeId, LocalDate cutOffDate);
}
package com.example.th.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.th.model.Payslip;

public interface PayslipRepository extends MongoRepository<Payslip, String> {
    List<Payslip> findByEmpIdAndMonth(String empId, String month);
}

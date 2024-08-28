package com.example.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.th.model.Payslip;
import com.example.th.repository.PayslipRepository;

@Service
public class PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    public List<Payslip> uploadPayslips(List<Payslip> payslips) {
        return payslipRepository.saveAll(payslips);
    }

    public List<Payslip> filterPayslipsByEmpIdAndMonth(String empId, String month) {
        return payslipRepository.findByEmpIdAndMonth(empId, month);
    }
}
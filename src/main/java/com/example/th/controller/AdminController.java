package com.example.th.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.th.model.Payslip;
import com.example.th.model.TimeOffRequest;
import com.example.th.service.PayslipService;
import com.example.th.service.TimeOffRequestService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PayslipService payslipService;

    @Autowired
    private TimeOffRequestService leaveRequestService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Admin logged in successfully");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Endpoint for bulk payslip upload
    @PostMapping("/payslips/upload")
    public List<Payslip> uploadPayslips(@RequestBody List<Payslip> payslips) {
        return payslipService.uploadPayslips(payslips);
    }

    // Endpoint for filtering leave requests by status
    @GetMapping("/leaverequests/filter")
    public List<TimeOffRequest> filterLeaveRequests(@RequestParam String status) {
        return leaveRequestService.filterLeaveRequests(status);
    }

    // Endpoint for filtering payslips by employee ID and month
    @GetMapping("/payslips/filter")
    public List<Payslip> filterPayslips(@RequestParam String empId, @RequestParam String month) {
        return payslipService.filterPayslipsByEmpIdAndMonth(empId, month);
    }
}
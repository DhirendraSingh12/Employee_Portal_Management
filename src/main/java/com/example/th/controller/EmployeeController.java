package com.example.th.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.th.model.Documents;
import com.example.th.model.Employee;
import com.example.th.model.TimeOffRequest;
import com.example.th.model.Timesheet;
import com.example.th.service.DocumentService;
import com.example.th.service.TimeOffRequestService;
import com.example.th.service.TimesheetService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private DocumentService documentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestParam String employeeId, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employeeId, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Employee logged in successfully");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    @PostMapping("/documents/upload")
    public Documents uploadDocument(@RequestBody Documents document) {
        return documentService.uploadDocument(document);
    }

    @PutMapping("/documents/verify/{documentId}")
    public Documents verifyDocument(@PathVariable String documentId, @RequestParam String status) {
        return documentService.verifyDocument(documentId, status);
    }
    
    
    @Autowired
    private TimeOffRequestService leaveRequestService;

    @PostMapping("/leaverequests/submit")
    public TimeOffRequest submitLeaveRequest(@RequestBody TimeOffRequest leaveRequest) {
        return leaveRequestService.submitLeaveRequest(leaveRequest);
    }

    @PutMapping("/leaverequests/approve/{leaveRequestId}")
    public TimeOffRequest approveLeaveRequest(@PathVariable String leaveRequestId, @RequestParam String status) {
        return leaveRequestService.approveLeaveRequest(leaveRequestId, status);
    }

    @GetMapping("/leaverequests/filter")
    public List<TimeOffRequest> filterLeaveRequests(@RequestParam String status) {
        return leaveRequestService.filterLeaveRequests(status);
    }
    
    
    @Autowired
    private TimesheetService timesheetService;

    @PostMapping("/timesheets/submit")
    public Timesheet submitTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.submitTimesheet(timesheet);
    }

    @GetMapping("/timesheets/pendingWarning/{employeeId}")
    public void sendPendingTimesheetWarning(@PathVariable String employeeId) {
        timesheetService.sendPendingTimesheetWarning(employeeId);
    }
    
}

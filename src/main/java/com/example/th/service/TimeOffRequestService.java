package com.example.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.th.model.TimeOffRequest;
import com.example.th.repository.TimeOffRequestRepository;

@Service
public class TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepository leaveRequestRepository;

    public TimeOffRequest submitLeaveRequest(TimeOffRequest  leaveRequest) {
        leaveRequest.setStatus("PENDING");
        return leaveRequestRepository.save(leaveRequest);
    }

    public TimeOffRequest  approveLeaveRequest(String leaveRequestId, String status) {
    	TimeOffRequest  leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        leaveRequest.setStatus(status);
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<TimeOffRequest > filterLeaveRequests(String status) {
        return leaveRequestRepository.findByStatus(status);
    }
}
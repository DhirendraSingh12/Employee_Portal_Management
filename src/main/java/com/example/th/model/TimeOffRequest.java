package com.example.th.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leave_requests")
public class TimeOffRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String partialDays; // "HALF_DAY" or "FULL_DAY"
    private String type; // "CASUAL", "SICK", "OTHER"
    private String reason; // Reason for the leave
    private String status; // "PENDING", "APPROVED", "REJECTED"
    
    // Getter and Setter 
    
    
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getPartialDays() {
		return partialDays;
	}
	public void setPartialDays(String partialDays) {
		this.partialDays = partialDays;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "TimeOffRequest [startDate=" + startDate + ", endDate=" + endDate + ", partialDays=" + partialDays
				+ ", type=" + type + ", reason=" + reason + ", status=" + status + "]";
	}
	
	
    
    
}
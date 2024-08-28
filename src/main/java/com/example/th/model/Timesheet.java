package com.example.th.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timesheets")
public class Timesheet {
	 private String employeeId;
    private LocalDate inDate; // Date of check-in
    private int inTimeHH; // Check-in hour (1-12)
    private int inTimeMM; // Check-in minute (0-59)
    private String inPeriod; // "AM" or "PM"
    private LocalDate outDate; // Date of check-out
    private int outTimeHH; // Check-out hour (1-12)
    private int outTimeMM; // Check-out minute (0-59)
    private String outPeriod; // "AM" or "PM"
    
    // Fields to be updated upon submission
    private String hours; // Total working hours for that day (in HH:MM format)
    private String attendanceStatus; // "Regularized", "Weekend", "Holiday"
    private String status; // "Approved"
    
    // Getter and Setter
    
    
	public LocalDate getInDate() {
		return inDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}
	public int getInTimeHH() {
		return inTimeHH;
	}
	public void setInTimeHH(int inTimeHH) {
		this.inTimeHH = inTimeHH;
	}
	public int getInTimeMM() {
		return inTimeMM;
	}
	public void setInTimeMM(int inTimeMM) {
		this.inTimeMM = inTimeMM;
	}
	public String getInPeriod() {
		return inPeriod;
	}
	public void setInPeriod(String inPeriod) {
		this.inPeriod = inPeriod;
	}
	public LocalDate getOutDate() {
		return outDate;
	}
	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}
	public int getOutTimeHH() {
		return outTimeHH;
	}
	public void setOutTimeHH(int outTimeHH) {
		this.outTimeHH = outTimeHH;
	}
	public int getOutTimeMM() {
		return outTimeMM;
	}
	public void setOutTimeMM(int outTimeMM) {
		this.outTimeMM = outTimeMM;
	}
	public String getOutPeriod() {
		return outPeriod;
	}
	public void setOutPeriod(String outPeriod) {
		this.outPeriod = outPeriod;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Timesheet [employeeId=" + employeeId + ", inDate=" + inDate + ", inTimeHH=" + inTimeHH + ", inTimeMM="
				+ inTimeMM + ", inPeriod=" + inPeriod + ", outDate=" + outDate + ", outTimeHH=" + outTimeHH
				+ ", outTimeMM=" + outTimeMM + ", outPeriod=" + outPeriod + ", hours=" + hours + ", attendanceStatus="
				+ attendanceStatus + ", status=" + status + "]";
	}
    
	
    
}

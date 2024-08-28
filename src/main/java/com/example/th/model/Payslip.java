package com.example.th.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payslips")
public class Payslip {
    @Id
    private String id;
    private String empId;
    private String month;
    private double amount;
    private String payslipUrl;
    
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPayslipUrl() {
		return payslipUrl;
	}
	public void setPayslipUrl(String payslipUrl) {
		this.payslipUrl = payslipUrl;
	}
	@Override
	public String toString() {
		return "Payslip [id=" + id + ", empId=" + empId + ", month=" + month + ", amount=" + amount + ", payslipUrl="
				+ payslipUrl + "]";
	}
    
    
}
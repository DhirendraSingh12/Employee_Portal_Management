package com.example.th.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String employeeId; // Auto-generated ID (e.g., HFX001)
    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;
    private String password; // This can be saved directly or encrypted, depending on your needs
    private String designation;
    private String department;
    private String address;
    private String email;
    private LocalDate dob;
    private String education;
    
    
	public Employee(String id, String employeeId, String firstName, String lastName, String gender, String mobile,
			String password, String designation, String department, String address, String email, LocalDate dob,
			String education) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.mobile = mobile;
		this.password = password;
		this.designation = designation;
		this.department = department;
		this.address = address;
		this.email = email;
		this.dob = dob;
		this.education = education;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", mobile=" + mobile + ", password=" + password + ", designation="
				+ designation + ", department=" + department + ", address=" + address + ", email=" + email + ", dob="
				+ dob + ", education=" + education + "]";
	}
    
	
    
}
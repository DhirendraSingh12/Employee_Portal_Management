package com.example.th.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.th.model.Employee;
import com.example.th.repository.EmployeeRepository;
import com.example.th.utils.EmployeeIdGenerator;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployeeIdGenerator employeeIdGenerator;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Employee createEmployee(Employee employee) {
        String employeeId = employeeIdGenerator.generate();
        employee.setEmployeeId(employeeId);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee = employeeRepository.save(employee);
        emailService.sendEmployeeCredentials(savedEmployee);
        return savedEmployee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(String employeeId, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setGender(employeeDetails.getGender());
        employee.setMobile(employeeDetails.getMobile());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setAddress(employeeDetails.getAddress());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDob(employeeDetails.getDob());
        employee.setEducation(employeeDetails.getEducation());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee findById(String employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
    }
}

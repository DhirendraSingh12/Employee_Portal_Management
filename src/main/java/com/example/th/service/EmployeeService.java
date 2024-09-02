package com.example.th.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    
    private static final String EMPLOYEE_ID_PREFIX = "HFX";
    private static final int ID_PADDING_SIZE = 4;

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

    public List<Employee> addEmployeesInBulk(List<Employee> employees) {
        // Get the current maximum employee ID number
        int currentMaxIdNumber = employeeRepository.findMaxEmployeeIdNumber(EMPLOYEE_ID_PREFIX);

        // Generate unique employee IDs and encrypt passwords
        List<Employee> processedEmployees = IntStream.range(0, employees.size())
                .mapToObj(i -> {
                    Employee employee = employees.get(i);

                    // Generate employee ID
                    String employeeId = generateEmployeeId(currentMaxIdNumber + i + 1);
                    employee.setEmployeeId(employeeId);

                    // Encrypt the password
                    String encodedPassword = passwordEncoder.encode(employee.getPassword());
                    employee.setPassword(encodedPassword);

                    // Send email with employee ID and raw password
                    emailService.sendEmployeeCredentials(employee.getEmail(), employeeId, employee.getPassword());

                    return employee;
                })
                .collect(Collectors.toList());

        // Save employees in the database
        return employeeRepository.saveAll(processedEmployees);
    }

    private String generateEmployeeId(int idNumber) {
        // Pads the number with leading zeros, e.g., 1 -> 0001
        String paddedNumber = String.format("%0" + ID_PADDING_SIZE + "d", idNumber);
        return EMPLOYEE_ID_PREFIX + paddedNumber;
    }
}

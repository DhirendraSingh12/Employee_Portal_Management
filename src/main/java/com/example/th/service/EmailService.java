package com.example.th.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.th.model.Employee;
import com.example.th.model.Timesheet;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPendingTimesheetEmail(String employeeId, List<Timesheet> pendingTimesheets) {
        // Fetch employee details (e.g., email) using employeeId
        String toEmail = getEmailForEmployee(employeeId);

        // Prepare email content
        String subject = "Pending Timesheets Reminder";
        String body = "You have pending timesheets from the last 5 days that need your attention.";

        // Send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    private String getEmailForEmployee(String employeeId) {
        // Fetch email from the employee repository or service
        return "employee@example.com"; // Replace with actual logic
    }
    
    public void sendEmployeeCredentials(Employee employee) {
        String subject = "Your Employee Credentials";
        String body = String.format("Dear %s %s, \n\nYour Employee ID: %s \nYour Password: %s \n\nPlease keep this information secure.",
                employee.getFirstName(), employee.getLastName(), employee.getEmployeeId(), employee.getPassword());

        sendEmail(employee.getEmail(), subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
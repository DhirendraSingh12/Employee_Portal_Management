package com.example.th.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.th.model.DialogueSession;
import com.example.th.model.Employee;
import com.example.th.repository.DialogueSessionRepository;
import com.example.th.repository.EmployeeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DialogueSessionService {
    @Autowired
    private DialogueSessionRepository repository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private JavaMailSender emailSender;  // Inject JavaMailSender

    private String getEmployeeEmail(String employeeId) {
        // Fetch the Employee object from the database using the employeeId
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        
        if (employeeOptional.isPresent()) {
            // Return the email address if the employee is found
            return employeeOptional.get().getEmail();
        } else {
            // Handle the case where the employee is not found
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
    }

    public DialogueSession createSession(DialogueSession session) {
        return repository.save(session);
    }

    public List<DialogueSession> getSessionsForEmployee(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public void sendEmailReminder(DialogueSession session) {
        // Create a SimpleMailMessage object
        SimpleMailMessage message = new SimpleMailMessage();
        
        // Set the email recipient
        message.setTo(getEmployeeEmail(session.getEmployeeId()));
        
        // Set the email subject
        message.setSubject("Reminder: Upcoming Meeting Scheduled on " + session.getNextMeetingDate());
        
        // Set the email body
        String emailBody = String.format(
                "Dear Employee, \n\n" +
                "This is a reminder for your upcoming %s on %s.\n\n" +
                "Meeting Details:\n" +
                "Meeting Type: %s\n" +
                "Review Date: %s\n" +
                "Comments: %s\n\n" +
                "Please be prepared and attend the meeting on time.\n\n" +
                "Best Regards,\n" +
                "Your HR Team",
                session.getMeetingType(),
                session.getNextMeetingDate(),
                session.getMeetingType(),
                session.getReviewDate(),
                session.getCommentsAndNotes()
        );
        
        message.setText(emailBody);
        
        // Send the email
        emailSender.send(message);
    }


    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void checkForUpcomingMeetings() {
        Date tomorrow = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        List<DialogueSession> upcomingSessions = repository.findAll()
            .stream()
            .filter(session -> session.getNextMeetingDate().equals(tomorrow))
            .collect(Collectors.toList());
        
        for (DialogueSession session : upcomingSessions) {
            sendEmailReminder(session);
        }
    }
}


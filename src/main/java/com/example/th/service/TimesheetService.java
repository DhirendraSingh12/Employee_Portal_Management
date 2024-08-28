package com.example.th.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.th.model.Timesheet;
import com.example.th.repository.TimesheetRepository;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;
    
    @Autowired
    private EmailService emailService;

    public Timesheet submitTimesheet(Timesheet timesheet) {
        if (isWeekend(timesheet.getInDate())) {
            timesheet.setHours("");
            timesheet.setAttendanceStatus("Weekend");
            timesheet.setStatus("");
        } else {
            timesheet.setHours(calculateTotalHours(timesheet));
            timesheet.setAttendanceStatus("Regularized");
            timesheet.setStatus("Approved");
        }
        return timesheetRepository.save(timesheet);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private String calculateTotalHours(Timesheet timesheet) {
        LocalTime inTime = convertToLocalTime(timesheet.getInTimeHH(), timesheet.getInTimeMM(), timesheet.getInPeriod());
        LocalTime outTime = convertToLocalTime(timesheet.getOutTimeHH(), timesheet.getOutTimeMM(), timesheet.getOutPeriod());
        long minutes = java.time.Duration.between(inTime, outTime).toMinutes();
        long hours = minutes / 60;
        minutes %= 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    private LocalTime convertToLocalTime(int hour, int minute, String period) {
        if (period.equalsIgnoreCase("PM") && hour < 12) {
            hour += 12;
        } else if (period.equalsIgnoreCase("AM") && hour == 12) {
            hour = 0;
        }
        return LocalTime.of(hour, minute);
    }

    public void sendPendingTimesheetWarning(String employeeId) {
        List<Timesheet> timesheets = timesheetRepository.findPendingTimesheetsByEmployeeId(employeeId, LocalDate.now().minusDays(7));
        if (!timesheets.isEmpty()) {
            emailService.sendPendingTimesheetEmail(employeeId, timesheets);
        }
    }
}
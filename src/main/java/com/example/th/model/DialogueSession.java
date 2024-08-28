package com.example.th.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "dialogue_sessions")
public class DialogueSession {
    @Id
    private String id;

    @Field("meeting_type")
    private String meetingType;  // Use String instead of Enum for easier handling in MongoDB

    @Field("employee_id")
    private String employeeId;

    @Field("review_date")
    private Date reviewDate;

    @Field("comments_and_notes")
    private String commentsAndNotes;

    @Field("next_meeting_date")
    private Date nextMeetingDate;
    
 // Getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getCommentsAndNotes() {
		return commentsAndNotes;
	}

	public void setCommentsAndNotes(String commentsAndNotes) {
		this.commentsAndNotes = commentsAndNotes;
	}

	public Date getNextMeetingDate() {
		return nextMeetingDate;
	}

	public void setNextMeetingDate(Date nextMeetingDate) {
		this.nextMeetingDate = nextMeetingDate;
	}
	

	@Override
	public String toString() {
		return "DialogueSession [id=" + id + ", meetingType=" + meetingType + ", employeeId=" + employeeId
				+ ", reviewDate=" + reviewDate + ", commentsAndNotes=" + commentsAndNotes + ", nextMeetingDate="
				+ nextMeetingDate + "]";
	}

    
    
}

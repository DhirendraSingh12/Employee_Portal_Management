package com.example.th.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class Documents {
    private String documentType; // e.g., "PAN", "AADHAR"
    private String documentUrl; // URL to the uploaded document
    private String uploadStatus; // e.g., "UPLOADED", "NOT_UPLOADED"
    private String verificationStatus; // e.g., "VERIFIED", "NOT_VERIFIED", "PENDING"
    
    // Getter and Setter 
    
    
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	
	
	@Override
	public String toString() {
		return "Documents [documentType=" + documentType + ", documentUrl=" + documentUrl + ", uploadStatus="
				+ uploadStatus + ", verificationStatus=" + verificationStatus + "]";
	}
    
    
	
	
}

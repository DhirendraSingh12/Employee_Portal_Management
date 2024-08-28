package com.example.th.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.th.model.Documents;
import com.example.th.repository.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Documents uploadDocument(Documents document) {
        document.setUploadStatus("Uploaded");
        document.setVerificationStatus("Pending");
        return documentRepository.save(document);
    }

    public Documents verifyDocument(String documentId, String status) {
        Documents document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));
        document.setVerificationStatus(status);
        return documentRepository.save(document);
    }
}
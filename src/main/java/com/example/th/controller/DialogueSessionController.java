package com.example.th.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.th.model.DialogueSession;
import com.example.th.service.DialogueSessionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dialogue-sessions")
public class DialogueSessionController {
    @Autowired
    private DialogueSessionService service;

    @PostMapping("/create")
    public ResponseEntity<DialogueSession> createSession(@RequestBody DialogueSession session) {
        DialogueSession savedSession = service.createSession(session);
        return ResponseEntity.ok(savedSession);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DialogueSession>> getSessionsForEmployee(@PathVariable String employeeId) {
        List<DialogueSession> sessions = service.getSessionsForEmployee(employeeId);
        return ResponseEntity.ok(sessions);
    }
}

package com.ejemplo.heroku.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/status")
public class StatusController {

    public record StatusResponse(String status, String message, Instant timestamp) {}

    @GetMapping
    public ResponseEntity<StatusResponse> getStatus() {
        StatusResponse response = new StatusResponse(
                "UP",
                "New-message",
                Instant.now()
        );
        return ResponseEntity.ok(response);
    }
}

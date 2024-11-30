package com.example.apiDesafioSenai.exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(int value, String message, LocalDateTime now) {
    }
}

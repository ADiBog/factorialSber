package com.example.factorialsber.dto;

import java.util.Map;

public class ErrorResponseDto {
    private Map<String, String> errors;
    private String message;

    public ErrorResponseDto(Map<String, String> errors, String message) {
        this.errors = errors;
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

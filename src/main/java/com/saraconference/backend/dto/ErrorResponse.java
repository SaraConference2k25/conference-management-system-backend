package com.saraconference.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {
    private String message;
    private int statusCode;

    public ErrorResponse(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }
}

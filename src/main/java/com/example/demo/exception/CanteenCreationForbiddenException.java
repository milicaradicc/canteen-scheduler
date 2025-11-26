package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CanteenCreationForbiddenException extends RuntimeException {
    public CanteenCreationForbiddenException(String message) {
        super(message);
    }
}
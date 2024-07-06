package com.example.data.exception;

public class SchoolDataNotFoundException extends RuntimeException {
    public SchoolDataNotFoundException(String message) {
        super(message);
    }
}

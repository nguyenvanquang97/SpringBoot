package com.example.kiemtra.exeption;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
package com.example.demo.exceptions;

public class NoSuchOrderException extends RuntimeException{
    public NoSuchOrderException(String message) {
        super(message);
    }
}
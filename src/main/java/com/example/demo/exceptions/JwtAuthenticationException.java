package com.example.demo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends RuntimeException { // TODO: 03.04.2024 верни AuthenticationException
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}

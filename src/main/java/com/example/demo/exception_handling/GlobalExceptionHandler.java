package com.example.demo.exception_handling;

import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.JwtAuthenticationException;
import com.example.demo.exceptions.NoSuchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchException.class)
    public ResponseEntity<Object> handleNoSuchException(
            NoSuchException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Object> handleJwtAuthenticationException(JwtAuthenticationException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleJwtAuthenticationException(AuthenticationException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleJwtAuthenticationException(UsernameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            Exception exception){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}

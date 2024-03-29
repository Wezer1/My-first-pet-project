package com.example.demo.exception_handling;

import com.example.demo.dto.OrderIncorrectData;
import com.example.demo.exceptions.JwtAuthenticationException;
import com.example.demo.exceptions.NoSuchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(
            NoSuchException exception){
        OrderIncorrectData data = new OrderIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(
            Exception exception){
        OrderIncorrectData data = new OrderIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(
            JwtAuthenticationException exception){
        OrderIncorrectData data = new OrderIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
    }

    // TODO: 29.03.2024 ты не отлавливаешь  JwtAuthenticationException, в трелло пункт 6.a
}

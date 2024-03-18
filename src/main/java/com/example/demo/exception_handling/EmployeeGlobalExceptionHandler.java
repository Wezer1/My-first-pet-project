package com.example.demo.exception_handling;

import com.example.demo.dto.OrderIncorrectData;
import com.example.demo.exceptions.NoSuchOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(
            NoSuchOrderException exception){
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
}

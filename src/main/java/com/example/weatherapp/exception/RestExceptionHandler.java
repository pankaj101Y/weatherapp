package com.example.weatherapp.exception;

import com.example.weatherapp.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {



    @ExceptionHandler(AppException.class)
    public ResponseEntity<GenericResponse<?>> handleError(AppException ex) {
        log.error("error ",ex);
        GenericResponse<Object> response = new GenericResponse<>(ex.getMessage(), null);
        return new ResponseEntity<>(response, ex.getHttpStatusCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<?>> handleError(Exception ex) {
        log.error("error ",ex);
        GenericResponse<Object> response = new GenericResponse<>(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
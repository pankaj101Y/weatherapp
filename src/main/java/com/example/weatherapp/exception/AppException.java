package com.example.weatherapp.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

    private HttpStatus httpStatusCode;

    public AppException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.message);
        this.httpStatusCode = exceptionEnum.code;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}

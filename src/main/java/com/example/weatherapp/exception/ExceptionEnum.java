package com.example.weatherapp.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {
    INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST,"Invalid time range. Please check start and end time"),
    INVALID_TIME_INTERVAL(HttpStatus.BAD_REQUEST,"Time interval must be greater or equal to 3600");

    public final HttpStatus code;
    public final String message;
    ExceptionEnum(HttpStatus status, String msg) {
        code=status;
        message=msg;
    }
}

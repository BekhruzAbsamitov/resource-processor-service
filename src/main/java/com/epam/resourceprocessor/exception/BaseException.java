package com.epam.resourceprocessor.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{
    private HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

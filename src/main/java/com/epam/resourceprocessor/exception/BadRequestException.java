package com.epam.resourceprocessor.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends com.epam.resourceprocessor.exception.BaseException {
    public BadRequestException(String message) {

        super(message, HttpStatus.BAD_REQUEST);
    }
}

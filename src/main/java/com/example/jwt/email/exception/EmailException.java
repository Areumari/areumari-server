package com.example.jwt.email.exception;

import lombok.Getter;

@Getter
public class EmailException extends RuntimeException {
    private final EmailErrorCode errorCode;

    public EmailException(EmailErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
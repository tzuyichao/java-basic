package com.example.demo.service;

import lombok.Getter;

@Getter
public class UserQueryException extends Exception {
    private ErrorStatus status;

    public UserQueryException(ErrorStatus errorStatus) {
        super(errorStatus.message);
        this.status = errorStatus;
    }

    @Getter
    public enum ErrorStatus {
        NOT_FOUND("user not found via given id");

        private String message;
        ErrorStatus(String message) {
            this.message = message;
        }
    }
}

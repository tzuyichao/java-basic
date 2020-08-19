package com.example.demo.service;

import lombok.Getter;

public class UserQueryException extends Exception {
    @Getter
    private ErrorStatus status;

    public UserQueryException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.status = errorStatus;
    }

    public enum ErrorStatus {
        NOT_FOUND("user not found via given id");

        @Getter
        private String message;
        ErrorStatus(String message) {
            this.message = message;
        }
    }
}

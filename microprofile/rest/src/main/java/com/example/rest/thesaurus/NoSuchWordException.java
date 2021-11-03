package com.example.rest.thesaurus;

public class NoSuchWordException extends Exception {
    public NoSuchWordException(String message) {
        super(message);
    }
}

package com.example.rest.thesaurus;

public class WordAlreadyExistsException extends Exception {
    public WordAlreadyExistsException(String message) {
        super(message);
    }
}

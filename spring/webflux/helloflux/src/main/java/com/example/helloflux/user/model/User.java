package com.example.helloflux.user.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;

    public User() {}

    public User(String name) {
        this.id = 0L;
        this.name = name;
    }
}

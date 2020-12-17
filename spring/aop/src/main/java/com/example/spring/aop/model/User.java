package com.example.spring.aop.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private UserType type;
}

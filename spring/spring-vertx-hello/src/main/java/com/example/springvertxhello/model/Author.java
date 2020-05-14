package com.example.springvertxhello.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Author {
    private String firstName;
    private String lastName;
}

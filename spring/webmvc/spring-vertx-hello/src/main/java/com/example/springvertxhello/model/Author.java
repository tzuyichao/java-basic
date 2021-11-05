package com.example.springvertxhello.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Author {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
}

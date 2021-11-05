package com.example.jpa.demo.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class Book {
    @EmbeddedId
    private BookId bookId;
    private String author;
}

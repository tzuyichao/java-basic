package com.example.spring.aop.repository;

import com.example.spring.aop.model.Book;

public interface BookRepository {
    Book getBookById(Long id);
}

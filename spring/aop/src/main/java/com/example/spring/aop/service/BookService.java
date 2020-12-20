package com.example.spring.aop.service;

import com.example.spring.aop.model.Book;

public interface BookService {
    Book getBook(Long id, String accessToken);
}

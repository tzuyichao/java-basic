package com.example.spring.aop.service;

import com.example.spring.aop.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BookServiceImpl implements BookService{
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book getBook(Long id, String accessToken) {
        Book book = new Book();
        book.setId(id);
        book.setTitle("Vert.x in Action");
        book.setPrice(new BigDecimal(49.99));
        book.setCost(new BigDecimal(20.99));
        log.info("before return: ", book.toString());
        return book;
    }
}

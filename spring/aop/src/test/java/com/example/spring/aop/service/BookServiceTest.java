package com.example.spring.aop.service;

import com.example.spring.aop.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    BookService bookService;

    @Test
    void test_get_book() {
        final String accessToken = "token1";
        Book book = bookService.getBook(1L, accessToken);
        assertThat(book)
                .isNotNull()
                .satisfies(b -> {
                    System.out.println(b.toString());
                    assertThat(b.getCost()).isNull();
                });
    }
}

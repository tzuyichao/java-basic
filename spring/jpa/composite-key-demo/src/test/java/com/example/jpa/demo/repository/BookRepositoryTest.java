package com.example.jpa.demo.repository;

import com.example.jpa.demo.model.Book;
import com.example.jpa.demo.model.BookId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void test_find_by_title() {
        Book gofE = new Book();
        gofE.setBookId(new BookId("Design Patterns", "en"));
        gofE.setAuthor("GoF");
        bookRepository.save(gofE);
        Book gofZh = new Book();
        gofZh.setBookId(new BookId("Design Patterns", "zh"));
        gofZh.setAuthor("GoF");
        bookRepository.save(gofZh);
        Collection<Book> subjects = bookRepository.findByTitle("Design Patterns");
        assertThat(subjects)
                .isNotNull();
        assertThat(subjects.size())
                .isEqualTo(2);
    }
}

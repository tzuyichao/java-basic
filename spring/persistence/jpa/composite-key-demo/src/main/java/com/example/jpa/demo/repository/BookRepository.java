package com.example.jpa.demo.repository;

import com.example.jpa.demo.model.Book;
import com.example.jpa.demo.model.BookId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookRepository extends CrudRepository<Book, BookId> {
    @Query("SELECT book FROM Book book WHERE book.bookId.title = :title")
    Collection<Book> findByTitle(String title);
}

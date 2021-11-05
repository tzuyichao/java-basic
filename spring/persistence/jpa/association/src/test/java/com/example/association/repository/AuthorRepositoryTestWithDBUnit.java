package com.example.association.repository;

import com.example.association.model.Author;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
public class AuthorRepositoryTestWithDBUnit {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DatabaseSetup("create_author.xml")
    void givenInitialByDBUnit_whenFindByName() {
        Optional<Author> authorOptional = authorRepository.findByName("Yukihiro Matsumoto");
        assertThat(authorOptional).isPresent();
    }
}

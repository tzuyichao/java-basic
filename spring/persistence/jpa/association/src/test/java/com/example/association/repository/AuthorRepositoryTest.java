package com.example.association.repository;

import com.example.association.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired AuthorRepository authorRepository;

    @Test
    void inject_component_should_success() {
        assertThat(authorRepository).isNotNull();
    }

    @Test
    void givenAuthor_whenSearchByName() {
        Author author = new Author();
        author.setName("Enrich Gamma");
        authorRepository.save(author);

        assertThat(authorRepository.findByName("Enrich Gamma")).isPresent();
    }

    @Test
    void test_prePersist_annotation() {
        Author author = new Author();
        author.setName("Enrich Gamma");
        authorRepository.save(author);

        assertThat(author).satisfies(author1 -> {
            assertThat(author1.getId()).isNotNull();
            assertThat(author1.getCreated()).isNotNull();
            assertThat(author1.getModified()).isNotNull();
        });
    }
}

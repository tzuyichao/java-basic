package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Movie;
import com.example.neo4jdemo.movie.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataNeo4jTest
public class MovieRepositoryTest {
    private MovieRepository movieRepository;

    @Autowired
    public MovieRepositoryTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Test
    void test_repository() {
        assertNotNull(movieRepository);
    }

    @Test
    void test_get_all_movie() {
        for(Movie movie : movieRepository.findAll()) {
            System.out.println(movie.toString());
            System.out.println("==========");
            for(Person p : movie.getRoles()) {
                System.out.println(p.toString());
            }
            System.out.println("==========");
        }
    }
}

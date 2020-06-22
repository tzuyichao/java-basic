package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Movie;
import com.example.neo4jdemo.movie.model.Person;
import com.example.neo4jdemo.movie.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
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
            for(Role r : movie.getRoles()) {
                System.out.println(r.toString());
            }
            System.out.println("==========");
        }
    }

    @Test
    void test_create_movie() {
        Movie test = new Movie();
        test.setReleased(2020);
        test.setTitle("You never know");
        System.out.println(test.toString());
        Movie test_saved = movieRepository.save(test);
        System.out.println(test_saved);
        movieRepository.deleteById(test_saved.getId());
    }

    @Test
    void test_find_movie_by_title() {
        Movie movie = movieRepository.findByTitle("The Matrix");
        assertNotNull(movie);
        System.out.println(movie);
    }

    @Test
    void test_find_movie_title_containing() {
        Collection<Movie> movies = movieRepository.findByTitleContaining("matrix");
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
        for(Movie movie : movies) {
            System.out.println(movie);
        }
    }

    @Test
    void test_graph() {
        List<Map<String, Object>> graph = movieRepository.graph(10);
        assertNotNull(graph);
        assertTrue(graph.size() > 0);
        for(Map<String, Object> item : graph) {
            for(String key : item.keySet()) {
                Object val = item.get(key);
                if(val instanceof String[]) {
                    System.out.println(key + ":" + Arrays.asList((String[])val));
                } else {
                    System.out.println(key + ":" + val);
                }
            }
            System.out.println("------------");
        }
    }
}

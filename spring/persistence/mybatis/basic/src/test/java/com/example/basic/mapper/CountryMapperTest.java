package com.example.basic.mapper;

import com.example.basic.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper countryMapper;

    @Test
    public void test_getAll() {
        List<Country> countryList = countryMapper.getAll();
        assertThat(countryList)
                .isNotNull()
                .satisfies(countries -> {
                   for(Country country: countries) {
                       System.out.println(country);
                   }
                });
    }
}

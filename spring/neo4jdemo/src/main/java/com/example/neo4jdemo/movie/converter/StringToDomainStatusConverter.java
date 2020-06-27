package com.example.neo4jdemo.movie.converter;

import com.example.neo4jdemo.movie.model.DomainStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDomainStatusConverter implements Converter<String, DomainStatus> {
    @Override
    public DomainStatus convert(String s) {
        return DomainStatus.valueOf(s);
    }
}

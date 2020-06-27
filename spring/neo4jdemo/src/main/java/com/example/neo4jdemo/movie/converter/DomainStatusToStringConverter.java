package com.example.neo4jdemo.movie.converter;

import com.example.neo4jdemo.movie.model.DomainStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DomainStatusToStringConverter implements Converter<DomainStatus, String>  {
    @Override
    public String convert(DomainStatus status) {
        return status.name();
    }
}

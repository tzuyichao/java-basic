package com.example.conversiondemo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

@Log
public class StringToDateTimeConverter implements Converter<String, DateTime> {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateTimeFormatter;
    @Getter @Setter
    private String datePattern = DEFAULT_DATE_PATTERN;

    @PostConstruct
    public void init() {
        log.info("init converter...");
        dateTimeFormatter = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String s) {
        return dateTimeFormatter.parseDateTime(s);
    }
}

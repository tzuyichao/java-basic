package com.example.proxy.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class NERMessageConverter extends MappingJackson2HttpMessageConverter {
    public NERMessageConverter() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypeList);
    }
}

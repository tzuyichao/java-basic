package com.example.proxy;

import com.example.proxy.converter.NERMessageConverter;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.proxy.adapter")
public class ProjectConfig {
    @Bean
    public Decoder feignDecoder() {
        NERMessageConverter converter = new NERMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(converter);
        return new SpringDecoder(objectFactory);
    }
}

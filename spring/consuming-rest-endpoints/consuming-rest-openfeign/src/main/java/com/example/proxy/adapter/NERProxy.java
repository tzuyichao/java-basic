package com.example.proxy.adapter;

import com.example.proxy.model.SevenEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ner",
        url = "${ner.service.url}")
public interface NERProxy {
    @PostMapping(value="/RESTAPI/ner/7entity", headers = {"Accept=text/plain, application/json, application/*+json, */*"})
    SevenEntity ner(@RequestBody String text);
}

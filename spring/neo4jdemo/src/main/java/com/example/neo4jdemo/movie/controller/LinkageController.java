package com.example.neo4jdemo.movie.controller;

import com.example.neo4jdemo.movie.service.LinkageService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Log
@RestController
@RequestMapping("/linkage")
public class LinkageController {
    private LinkageService linkageService;

    public LinkageController(LinkageService linkageService) {
        this.linkageService = linkageService;
    }

    @GetMapping("export")
    public void export() {
        linkageService.export("match p=(s)-[:LINKAGE]->(t)\n" +
                        "where s.status= 'PUBLISHED' and t.status = 'PUBLISHED'\n" +
                        "return s.name as Term, labels(t)[1] as CatalogUnitLabel, t.name as CatalogUnitName",
                Collections.emptyMap(),
                "test.xlsx");
    }
}

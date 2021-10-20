package com.example.neo4jdemo.movie.service;

import com.example.neo4jdemo.movie.model.CatalogUnitType;
import com.example.neo4jdemo.movie.repository.DomainRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DomainService {
    private DomainRepository domainRepository;
    private ApplicationContext applicationContext;

    public DomainService(DomainRepository domainRepository, ApplicationContext applicationContext) {
        this.domainRepository = domainRepository;
        this.applicationContext = applicationContext;
    }

    public CatalogUnitService lookup(CatalogUnitType catalogUnitType) {
        CatalogUnitService catalogUnitService = (CatalogUnitService) applicationContext.getBean(catalogUnitType.val());
        return catalogUnitService;
    }
}

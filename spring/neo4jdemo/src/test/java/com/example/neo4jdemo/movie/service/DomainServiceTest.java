package com.example.neo4jdemo.movie.service;

import com.example.neo4jdemo.movie.model.CatalogUnitType;
import com.example.neo4jdemo.movie.repository.DomainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DomainServiceTest {
    @Mock
    private DomainRepository domainRepository;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private SystemUnitService systemUnitService;

    @InjectMocks
    private DomainService domainService;

    @Test
    void test_lookup_CatalogUnitService() {
        given(applicationContext.getBean(CatalogUnitType.System.val())).willReturn(systemUnitService);

        CatalogUnitService systemUnitService = domainService.lookup(CatalogUnitType.System);

        assertThat(systemUnitService).isNotNull();
    }
}

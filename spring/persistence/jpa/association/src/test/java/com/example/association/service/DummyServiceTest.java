package com.example.association.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DummyServiceTest {
    @Mock
    private TestService testService;

    @InjectMocks
    private DummyService dummyService = new DummyServiceImpl(testService);

    @Test
    void test_something() {
        given(testService.doSomething(1)).willReturn(false);

        assertThat(dummyService.createDummy(1)).isEqualTo(-1);
    }
}

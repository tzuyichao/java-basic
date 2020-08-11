package com.example.association.service;

import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {
    private TestService testService;

    public DummyServiceImpl(TestService testService) {
        this.testService = testService;
    }

    @Override
    public long createDummy(int type) {
        if(testService.doSomething(type)) {
            return 0;
        } else {
            return -1;
        }
    }
}

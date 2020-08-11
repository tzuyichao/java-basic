package com.example.association.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public boolean doSomething(int type) {
        if(type % 2 == 0) {
            return true;
        }
        return false;
    }
}

package main.service;

import org.springframework.stereotype.Service;

@Service
public class FooSimpleService implements SimpleService {

    @Override
    public void doSomething(String name) {
        System.out.println("FooSimpleService doSomething with " + name);
    }
}

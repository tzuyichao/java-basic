package main.service;

import org.springframework.stereotype.Service;

@Service
public class BlaSimpleService implements SimpleService{

    @Override
    public void doSomething(String name) {
        System.out.println("BlaSimpleService doSomething with " + name);
    }
}

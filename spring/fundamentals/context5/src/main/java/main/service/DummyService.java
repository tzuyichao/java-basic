package main.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyService {
    private List<SimpleService> simpleServiceList;

    public void test() {
        System.out.println(this);
        for(SimpleService simpleService:simpleServiceList) {
            simpleService.doSomething("test");
        }
    }
}

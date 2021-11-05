package com.example.demo;

import org.junit.jupiter.api.Test;

// https://stackoverflow.com/questions/46650268/how-to-test-main-class-of-spring-boot-application
public class MyApplicationIT {
    @Test
    public void main() {
        SpringRestDocsDemoApplication.main(new String[] {});
    }
}

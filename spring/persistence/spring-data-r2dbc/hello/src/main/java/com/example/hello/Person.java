package com.example.hello;

import org.springframework.data.annotation.Id;

public record Person(@Id String id, String name, int age) {

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

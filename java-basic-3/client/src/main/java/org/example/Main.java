package org.example;

import component.MyComponent;
import component.MyComponentFactory;

public class Main {
    public static void main(String[] args) {
        MyComponent myComponent = MyComponentFactory.create();
        myComponent.execute();
    }
}
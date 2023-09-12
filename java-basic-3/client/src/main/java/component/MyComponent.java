package component;

import annotation.Factory;

@Factory
public class MyComponent {
    public void execute() {
        System.out.println("I am running...");
    }
}

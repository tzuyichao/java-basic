package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var x = new Parrot();
        x.setName("kiki");
        Supplier<Parrot> parrotSupplier = () -> x;

        context.registerBean("parrot1", Parrot.class, parrotSupplier);

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p == x);
        System.out.println(p);

        Dog d1 = new Dog();
        d1.setName("lucky");
        Supplier<Dog> dog1Supplier = () -> d1;

        Dog d2 = new Dog();
        d2.setName("kuga");
        Supplier<Dog> dog2Supplier = () -> d2;

        context.registerBean("dog1", Dog.class, dog1Supplier, bd -> bd.setPrimary(true));
        context.registerBean("dog2", Dog.class, dog2Supplier);

        Dog dog = context.getBean(Dog.class);
        System.out.println(dog);
    }
}

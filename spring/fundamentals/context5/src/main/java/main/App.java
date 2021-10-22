package main;

import main.beans.Person;
import main.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person p = context.getBean("person", Person.class);

        System.out.println("Person's name:" + p.getName());
        System.out.println("Person's parrot:" + p.getParrot());
    }
}

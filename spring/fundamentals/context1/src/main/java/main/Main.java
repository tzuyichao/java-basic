package main;

import beans.Computer;
import beans.Dog;
import beans.Parrot;
import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());

        Computer c = context.getBean(Computer.class);
        System.out.println(c.getName());

        Dog miki = context.getBean("miki", Dog.class);
        System.out.println(miki.getName());

        Dog mini = (Dog) context.getBean("mini");
        System.out.println(mini.getName());
    }
}

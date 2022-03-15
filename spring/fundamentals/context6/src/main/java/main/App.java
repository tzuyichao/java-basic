package main;

import main.service.DummyService;
import main.service.SimpleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var cs1 = context.getBean("commentService", CommentService.class);
        var cs2 = context.getBean("commentService", CommentService.class);

        boolean check = cs1 == cs2;
        System.out.println(check);

        for(String name: context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        var dummyService = context.getBean("dummyService", DummyService.class);
        dummyService.test();
    }
}

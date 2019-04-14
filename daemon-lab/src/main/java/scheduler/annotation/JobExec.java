package scheduler.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JobExec {

    String name() default "";

    String group() default "default";

    String cronExpression() default "";
}

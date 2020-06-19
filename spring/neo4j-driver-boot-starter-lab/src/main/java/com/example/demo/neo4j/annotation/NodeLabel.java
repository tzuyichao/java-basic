package com.example.demo.neo4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface NodeLabel {
    String value() default "";
}

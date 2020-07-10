package com.example.conversiondemo.conversion;

import lombok.extern.java.Log;
import org.springframework.context.support.GenericXmlApplicationContext;

@Log
public class PropertyEditorDemo {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.load("classpath:spring/prop-editor-app-context.xml");
            ctx.refresh();
            Singer eric = ctx.getBean("eric", Singer.class);
            log.info(eric.toString());
            Singer countrySinger = ctx.getBean("countrySinger", Singer.class);
            log.info(countrySinger.toString());
        }
    }
}

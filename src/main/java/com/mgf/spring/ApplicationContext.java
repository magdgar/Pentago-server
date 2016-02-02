package com.mgf.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext {
    private static org.springframework.context.ApplicationContext applicationContext;

    public static org.springframework.context.ApplicationContext getApplicationContext() {
        if(applicationContext == null)
            applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        return applicationContext;

    }
    private ApplicationContext() {
    }
}

package com.ihorpolataiko;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.util.Objects.isNull;

public class ApplicationContextHolder {

    private static ApplicationContext applicationContext;

    public static void init() {
        applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
    }

    public static AutowireCapableBeanFactory getAutowireCapableBeanFactory() {

//        if(isNull(applicationContext)) {
//            applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
//        }

        return applicationContext.getAutowireCapableBeanFactory();
    }
}

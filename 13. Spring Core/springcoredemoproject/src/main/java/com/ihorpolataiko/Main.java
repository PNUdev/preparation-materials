package com.ihorpolataiko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        Holder holder = applicationContext.getBean(Holder.class);
        holder.doJob();

    }

}

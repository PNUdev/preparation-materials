package com.ihorpolataiko;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextStartupListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContextHolder.init();
    }
}

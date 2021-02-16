package com.pnudev.RestSecurityJwt.listener;

import com.pnudev.RestSecurityJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupAppListener {

    @Autowired
    private UserService userService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userService.createAdminIfNotExists();
    }
}

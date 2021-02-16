package com.pnudev.RestSecurityBasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/a")
    public String a() {
        return "a!";
    }
}

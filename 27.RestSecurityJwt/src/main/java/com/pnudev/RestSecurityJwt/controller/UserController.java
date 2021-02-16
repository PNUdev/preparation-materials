package com.pnudev.RestSecurityJwt.controller;

import com.pnudev.RestSecurityJwt.dto.CreateUserForm;
import com.pnudev.RestSecurityJwt.model.User;
import com.pnudev.RestSecurityJwt.model.UserRole;
import com.pnudev.RestSecurityJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public boolean create(@RequestBody CreateUserForm createUserForm, @AuthenticationPrincipal User user) {
        if (user.getRole() != UserRole.ROLE_ADMIN) {
            return false;
        }
        return userService.create(createUserForm);
    }

    @GetMapping()
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }



}

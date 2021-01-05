package com.ihorpolataiko.springsecuritydemoforwebapp.controller;

import com.ihorpolataiko.springsecuritydemoforwebapp.domain.User;
import com.ihorpolataiko.springsecuritydemoforwebapp.dto.CreateUserForm;
import com.ihorpolataiko.springsecuritydemoforwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(@AuthenticationPrincipal User user, Model model) { // /**/inbox

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "user/index";
    }

    @PostMapping("/new")
    public String create(CreateUserForm createUserForm) {

        userService.create(createUserForm);
        return "redirect:/users";
    }

}

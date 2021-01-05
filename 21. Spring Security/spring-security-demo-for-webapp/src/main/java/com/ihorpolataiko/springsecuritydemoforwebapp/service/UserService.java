package com.ihorpolataiko.springsecuritydemoforwebapp.service;

import com.ihorpolataiko.springsecuritydemoforwebapp.domain.User;
import com.ihorpolataiko.springsecuritydemoforwebapp.dto.CreateUserForm;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void create(CreateUserForm createUserForm);

}

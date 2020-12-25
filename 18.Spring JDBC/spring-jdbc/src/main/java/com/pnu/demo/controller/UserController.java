package com.pnu.demo.controller;

import com.pnu.demo.dao.UserDao;
import com.pnu.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userDao.create(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        return userDao.update(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userDao.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        userDao.deleteById(id);
    }

    @GetMapping
    public List<User> getAll(){
        return userDao.getAll();
    }
}

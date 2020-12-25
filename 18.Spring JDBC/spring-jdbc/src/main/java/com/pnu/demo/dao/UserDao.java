package com.pnu.demo.dao;

import com.pnu.demo.model.User;

import java.util.List;

public interface UserDao {

    User create(User user);

    User update(User user);

    User getById(Long id);

    void deleteById(Long id);

    List<User> getAll();

}

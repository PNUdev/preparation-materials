package com.ihorpolataiko.springsecuritydemoforwebapp.repository;

import com.ihorpolataiko.springsecuritydemoforwebapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByUsername(String username);

}

package com.pnudev.RestSecurityBasic.service;

import com.pnudev.RestSecurityBasic.model.User;
import com.pnudev.RestSecurityBasic.model.UserRole;
import com.pnudev.RestSecurityBasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.builder()
                .active(true)
                .id("id")
                .role(UserRole.ROLE_USER)
                .username("user")
                .password(passwordEncoder.encode("user"))
                .build();
    }
}

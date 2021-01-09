package com.ihorpolataiko.springsecuritydemoforwebapp.service;

import com.ihorpolataiko.springsecuritydemoforwebapp.domain.User;
import com.ihorpolataiko.springsecuritydemoforwebapp.domain.UserRole;
import com.ihorpolataiko.springsecuritydemoforwebapp.dto.CreateUserForm;
import com.ihorpolataiko.springsecuritydemoforwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void create(CreateUserForm createUserForm) {

        User user = User.builder()
                .username(createUserForm.getUsername())
                .password(passwordEncoder.encode(createUserForm.getPassword()))
                .role(UserRole.ROLE_USER)
                .active(true)
                .build();

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}

package com.pnudev.RestSecurityJwt.service;

import com.pnudev.RestSecurityJwt.dto.CreateUserForm;
import com.pnudev.RestSecurityJwt.model.User;
import com.pnudev.RestSecurityJwt.model.UserRole;
import com.pnudev.RestSecurityJwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public boolean create(CreateUserForm createUserForm) {

        if (userRepository.existsByUsername(createUserForm.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        User user = User.builder()
                .username(createUserForm.getUsername())
                .password(passwordEncoder.encode(createUserForm.getPassword()))
                .role(UserRole.ROLE_USER)
                .active(true)
                .build();

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean createAdminIfNotExists() {
        if (!userRepository.existsByUsername("admin")) {
            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(UserRole.ROLE_ADMIN)
                    .active(true)
                    .build();

            userRepository.save(user);
            return true;
        }
        return false;
    }
}

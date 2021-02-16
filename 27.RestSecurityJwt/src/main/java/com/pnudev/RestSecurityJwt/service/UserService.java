package com.pnudev.RestSecurityJwt.service;

import com.pnudev.RestSecurityJwt.dto.CreateUserForm;

public interface UserService {

    boolean create(CreateUserForm createUserForm);

    boolean createAdminIfNotExists();
}

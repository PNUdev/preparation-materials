package com.pnu.restintro.service;

import com.pnu.restintro.dto.UserDto;

public interface UserService {

    UserDto create(UserDto User);

    UserDto update(UserDto User);

    UserDto getById(Long id);

    UserDto getByEmail(String email);

    void delete(Long id);

}

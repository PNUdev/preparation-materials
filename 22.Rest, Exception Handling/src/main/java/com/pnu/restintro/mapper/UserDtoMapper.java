package com.pnu.restintro.mapper;

import com.pnu.restintro.dto.UserDto;
import com.pnu.restintro.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDto toDto(User user){

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .active(user.getActive())
                .build();
    }

    public User toUser(UserDto user){

        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .active(user.getActive())
                .build();
    }
}

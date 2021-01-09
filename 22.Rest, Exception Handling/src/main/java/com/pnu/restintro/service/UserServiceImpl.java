package com.pnu.restintro.service;

import com.pnu.restintro.dto.UserDto;
import com.pnu.restintro.exception.EntityDuplicationException;
import com.pnu.restintro.exception.UserNotFoundException;
import com.pnu.restintro.mapper.UserDtoMapper;
import com.pnu.restintro.repository.UserRepository;
import com.pnu.restintro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserDtoMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDtoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public UserDto create(UserDto user) {

        try {
            return mapper.toDto(userRepository.save(mapper.toUser(user)));
        } catch (DataIntegrityViolationException e) {
            throw new EntityDuplicationException("User with a specified email exists!");
        }

    }

    @Override
    public UserDto update(UserDto user) {

        User persistentUser = getByIdOrThrowException(user.getId());
        persistentUser.setFirstName(user.getFirstName());
        persistentUser.setLastName(user.getLastName());
        return mapper.toDto(userRepository.save(persistentUser));

    }

    @Override
    public UserDto getById(Long id) {
        return mapper.toDto(getByIdOrThrowException(id));
    }

    @Override
    public UserDto getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with a specified email not found!"));
    }

    @Override
    public void delete(Long id) {
        getByIdOrThrowException(id);
        userRepository.deleteById(id);
    }

    private User getByIdOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with a specified id not found!"));
    }

}

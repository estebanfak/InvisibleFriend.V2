package com.prueba.AmigoInvisible.service;

import com.prueba.AmigoInvisible.dto.NewUserDto;
import com.prueba.AmigoInvisible.dto.UserDto;
import com.prueba.AmigoInvisible.entity.User;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto newUser(NewUserDto newUserDto);
    UserDto getCurrentUser(Authentication authentication) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    void save(User user);
}

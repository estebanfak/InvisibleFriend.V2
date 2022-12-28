package com.prueba.AmigoInvisible.service.implement;

import com.prueba.AmigoInvisible.constants.Messages;
import com.prueba.AmigoInvisible.dto.NewUserDto;
import com.prueba.AmigoInvisible.dto.UserDto;
import com.prueba.AmigoInvisible.entity.User;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import com.prueba.AmigoInvisible.repository.UserRepository;
import com.prueba.AmigoInvisible.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }
    @Override
    public UserDto newUser(NewUserDto newUserDto) {
        User user = new User(newUserDto.getName(), newUserDto.getLastName(), newUserDto.getEmail(), passwordEncoder.encode(newUserDto.getPassword()));
        return new UserDto(userRepository.save(user));
    }
    @Override
    public UserDto getCurrentUser(Authentication authentication) throws UserNotFoundException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UserNotFoundException(Messages.USER_NOT_FOUND));
        return new UserDto(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(Messages.USER_NOT_FOUND));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
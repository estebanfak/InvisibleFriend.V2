package com.prueba.AmigoInvisible.controller;

import com.prueba.AmigoInvisible.dto.LoginDTO;
import com.prueba.AmigoInvisible.dto.NewUserDto;
import com.prueba.AmigoInvisible.entity.MyUserDetails;
import com.prueba.AmigoInvisible.exception.UserNotFoundException;
import com.prueba.AmigoInvisible.service.MyUserDetailService;
import com.prueba.AmigoInvisible.service.UserService;
import com.prueba.AmigoInvisible.utility.JwtUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Valid
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtUtility jwtUtility;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getCurrentUser(authentication));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDTO loginDTO) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        final MyUserDetails myUserDetails = (MyUserDetails) myUserDetailService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtUtility.generateToken(myUserDetails);

        return ResponseEntity.ok(jwt);
    }
    @PostMapping
    public ResponseEntity<?> newUser(@Validated @RequestBody NewUserDto newUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.newUser(newUserDto));
    }

}

package com.springboot.crud.mysqlspring.domain.user.controller;

import com.springboot.crud.mysqlspring.domain.user.dto.UserDto;
import com.springboot.crud.mysqlspring.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // user registration
    @PostMapping
    public UserDto registration(@RequestBody @Valid UserDto.Registration registration) {
        return userService.registration(registration);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid UserDto.Login login) {
        return userService.login(login);
    }



}
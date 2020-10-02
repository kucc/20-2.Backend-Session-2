package com.todo.demo.controller;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public User signUp(UserDto.Request requestDto) throws Exception{

        if(!authService.emailCheck(requestDto.getEmail())){
            throw new Exception();
        }

        User user = authService.createUser(requestDto);
        return user;
    }
}

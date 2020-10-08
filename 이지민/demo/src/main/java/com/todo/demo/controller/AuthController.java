package com.todo.demo.controller;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.exception.CustomUserNotFoundException;
import com.todo.demo.model.User;
import com.todo.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto.Response> signUp(UserDto.Request requestDto) throws Exception{

        if(!authService.emailDoesNotExist(requestDto.getEmail())){
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if(!requestDto.getPassword().equals(requestDto.getConfirmedPassword())){
            throw new Exception("비밀번호를 확인해주세요.");
        }

        User user = authService.createUser(requestDto);
        return ResponseEntity.ok().body(new UserDto.Response(user));
    }

    @PostMapping("/login")
    public String login(UserDto.Login loginDto){

        if(authService.emailDoesNotExist(loginDto.getEmail())){
            throw new CustomUserNotFoundException("가입되지 않은 email 입니다.");
        }

        if(!authService.passwordMatch(loginDto.getPassword(), loginDto.getEmail())){
            throw new CustomUserNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        return authService.createTokenById(loginDto.getEmail());
    }
}

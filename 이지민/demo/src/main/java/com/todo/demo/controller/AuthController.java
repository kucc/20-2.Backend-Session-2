package com.todo.demo.controller;

import com.todo.demo.dto.UserDto;
import com.todo.demo.exception.CustomUserNotFoundException;
import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto.Response> signUp(@RequestBody UserDto.Request requestDto) throws Exception{

        if(!userService.emailDoesNotExist(requestDto.getEmail())){
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if(!requestDto.getPassword().equals(requestDto.getConfirmedPassword())){
            throw new Exception("비밀번호를 확인해주세요.");
        }

        int userId = userService.createUser(requestDto);
        User user = userService.findOne(userId);
        return ResponseEntity.ok().body(new UserDto.Response(user));
    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody UserDto.Login loginDto){

        if(userService.emailDoesNotExist(loginDto.getEmail())){
            throw new CustomUserNotFoundException("가입되지 않은 email 입니다.");
        }

        if(!userService.passwordMatch(loginDto.getPassword(), loginDto.getEmail())){
            throw new CustomUserNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        String token = userService.createTokenById(loginDto.getEmail());
        return ResponseEntity.ok().body(token);
    }
}

package com.todo.demo.controller;

import com.todo.demo.dto.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="")
    public ResponseEntity<List<UserDto.Response>> getUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(UserDto.Response.of(users));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UserDto.Response> getOneUser(@PathVariable int id){
        User user = userService.findOne(id);
        return ResponseEntity.ok().body(UserDto.Response.of(user));
    }

    @GetMapping(path="/me")
    public ResponseEntity<UserDto.Response> getUserInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.ok().body(UserDto.Response.of(user));
    }


}

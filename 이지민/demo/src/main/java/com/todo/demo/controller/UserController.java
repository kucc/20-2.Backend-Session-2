package com.todo.demo.controller;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UserDto.Response> getOneUser(@PathVariable long id){
        User user = userService.findOne(id);

        return ResponseEntity.ok().body(new UserDto.Response(user));
    }


}

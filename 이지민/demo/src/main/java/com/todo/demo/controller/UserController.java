package com.todo.demo.controller;

import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="")
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping(path="/{id}")
    public User getOneUser(@PathVariable long id){
        User user = userService.findOne(id);

        return user;
    }


}

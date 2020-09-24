package com.todo.demo.controller;

import com.todo.demo.model.User;
import com.todo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path="")
    public List<User> getUsers(){
        System.err.println("UserController getUsers");
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping(path="/{id}")
    public User getOneUser(@PathVariable("id") long id){
        System.err.println("UserController getOneUser");
        Integer select_id = (int)id;
        System.err.println(select_id);
        return userService.findOne(select_id);
    }


}

package com.todo.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String getOneUser(@PathVariable("id") long id) throws JsonProcessingException {
        System.err.println("UserController getOneUser");
        Integer select_id = (int)id;

        User user = userService.findOne(select_id);
        ObjectMapper objectMapper = new ObjectMapper();

        String userJson = objectMapper.writeValueAsString(user);
        return userJson;
    }


}

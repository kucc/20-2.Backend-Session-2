package com.todo.demo.service;

import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<User> list = userRepository.findAll();

        for(User user: list){
            System.out.println(user.toString());
        }

        return list;
    }

    public User findOne(long id){
        User user = userRepository.getOne(id);
        System.out.println(user);
        return user;
    }
}

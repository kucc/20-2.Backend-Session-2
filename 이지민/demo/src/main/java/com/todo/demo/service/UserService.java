package com.todo.demo.service;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        return list;
    }

    public User findOne(long id){
        User user = userRepository.getOne(id);;
        //UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return user;
    }

}

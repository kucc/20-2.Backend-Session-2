package com.todo.demo.service;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import com.todo.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        return list;
    }

    public User findOne(int id){
        User user = userRepository.getOne(id);

        return user;
    }

    public User createUser(UserDto.Request requestDto){
    public Integer createUser(UserDto.Request requestDto){
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = requestDto.toEntity();
        userRepository.save(user);
        return user;
    }

    public Boolean emailDoesNotExist(String email){
        return userRepository.findByEmail(email) == null;
    }

    public Boolean passwordMatch(String password, String email){
        User user = userRepository.findByEmail(email);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String createTokenById(String email){
        User user = userRepository.findByEmail(email);
        return jwtTokenProvider.createToken(String.valueOf(user.getId()));
    }

}

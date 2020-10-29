package com.todo.demo.service;

import com.todo.demo.dto.UserDto;
import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.model.UserHasCategory;
import com.todo.demo.repository.UserRepository;
import com.todo.demo.repository.UserRepositorySupport;
import com.todo.demo.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepositorySupport userRepositorySupport;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepositorySupport userRepositorySupport) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepositorySupport = userRepositorySupport;
    }

    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        return list;
    }

    public User findOne(int id){
        User user = userRepository.getOne(id);
        return user;
    }

    @Transactional
    public Integer createUser(UserDto.Request requestDto){
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = requestDto.toEntity();
        return userRepository.save(user).getId();
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

    public List<User> getUsersByCategoryId(int categoryId){
        return userRepositorySupport.findUsersByCategoryId(categoryId);
    }
    
    public boolean isJoined(Category category, User user){
        int categoryId = category.getId();
        List<User> userList = userRepositorySupport.findUsersByCategoryId(categoryId);
        return userList.contains(user);
    }

}
